package com.runtracker.services;

import com.runtracker.models.User;
import com.runtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   public Optional<User> getUserByEmail(String email) {
      return userRepository.findByEmail(email);
   }

   public User createUser(User user) {

      // Hash password sebelum menyimpan ke database
      String hashedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(hashedPassword);

      // Tetapkan role default jika belum diset
      if (user.getRole() == null) {
         user.setRole(User.Role.MEMBER);
      }

      // Simpan user ke repository
      return userRepository.save(user);
   }

}
