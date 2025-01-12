package com.runtracker.services;

import com.runtracker.dao.UserDAO;
import com.runtracker.models.User;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   @Autowired
   private UserDAO userDAO;

   public User authenticate(String email, String password) {
      Optional<User> userOpt = userDAO.findByEmail(email);
      if (userOpt.isEmpty() || !password.equals(userOpt.get().getPassword())) {
         throw new IllegalArgumentException("Invalid email or password.");
      }
      return userOpt.get();
   }

   public boolean isEmailTaken(String email) {
      return userDAO.findByEmail(email).isPresent();
   }

   public void createUser(User user) {
      if (isEmailTaken(user.getEmail())) {
         throw new IllegalArgumentException("Email is already in use.");
      }
      userDAO.save(user);
   }

   public void updateProfile(Long userId, User updatedDetails) {
      User existingUser = userDAO.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

      existingUser.setName(updatedDetails.getName());
      existingUser.setEmail(updatedDetails.getEmail());

      if (updatedDetails.getPassword() != null && !updatedDetails.getPassword().isEmpty()) {
         existingUser.setPassword(updatedDetails.getPassword());
      }

      userDAO.update(existingUser);
   }

   public void deleteUser(Long userId) {
      if (!userDAO.findById(userId).isPresent()) {
         throw new IllegalArgumentException("User not found");
      }
      userDAO.delete(userId);
   }

   public Optional<User> getUserByEmail(String email) {
      return userDAO.findByEmail(email);
   }
}
