package com.runtracker.controllers;

import com.runtracker.models.User;
import com.runtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;

   // Render halaman signup
   @GetMapping("/signup")
   public String renderSignupPage() {
      return "signup";
   }

   // Render halaman signin
   @GetMapping("/signin")
   public String renderSigninPage() {
      return "signin";
   }

   // // Render halaman profile
   // @GetMapping("/profile")
   // public String renderProfilePage(HttpSession session, Model model) {
   // User loggedInUser = (User) session.getAttribute("loggedInUser");
   // if (loggedInUser == null) {
   // return "redirect:/user/signin";
   // }
   // model.addAttribute("user", loggedInUser);
   // return "profile";
   // }

   // Handle signup
   @PostMapping("/signup")
   public String signup(@ModelAttribute User user, Model model) {
      System.out.println("Received signup data: " + user);

      if (userService.getUserByEmail(user.getEmail()).isPresent()) {
         model.addAttribute("error", "Email is already in use.");
         return "signup";
      }

      userService.createUser(user);

      return "redirect:/user/signin";
   }

   // Handle signin
   @PostMapping("/signin")
   public String signin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
      // Cari user berdasarkan email
      Optional<User> optionalUser = userService.getUserByEmail(email);

      if (optionalUser.isEmpty()) {
         model.addAttribute("error", "Invalid email or password.");
         return "signin"; // Kembali ke halaman signin dengan pesan error
      }

      User user = optionalUser.get();

      // Periksa password
      if (!passwordEncoder.matches(password, user.getPassword())) {
         model.addAttribute("error", "Invalid email or password.");
         return "signin";
      }

      // Simpan user ke session
      session.setAttribute("loggedInUser", user);

      return "redirect:/user/profile"; // Redirect ke halaman profile setelah berhasil signin
   }

   // // Handle signout
   // @PostMapping("/signout")
   // public String signout(HttpSession session) {
   // session.invalidate();
   // return "redirect:/user/signin";
   // }

   // // Handle update profile
   // @PutMapping("/profile")
   // public ResponseEntity<String> update(@ModelAttribute User userDetails,
   // HttpSession session) {
   // User loggedInUser = (User) session.getAttribute("loggedInUser");
   // if (loggedInUser == null) {
   // return ResponseEntity.status(403).body("Unauthorized");
   // }
   // loggedInUser.setName(userDetails.getName());
   // loggedInUser.setEmail(userDetails.getEmail());
   // if (userDetails.getPassword() != null &&
   // !userDetails.getPassword().isEmpty()) {
   // loggedInUser.setPassword(userDetails.getPassword());
   // }
   // userService.saveUser(loggedInUser);
   // return ResponseEntity.ok("Profile updated successfully");
   // }

   // // Handle delete account
   // @DeleteMapping("/profile")
   // public ResponseEntity<String> delete(HttpSession session) {
   // User loggedInUser = (User) session.getAttribute("loggedInUser");
   // if (loggedInUser == null) {
   // return ResponseEntity.status(403).body("Unauthorized");
   // }
   // userService.deleteUser(loggedInUser.getId());
   // session.invalidate();
   // return ResponseEntity.ok("Account deleted successfully");
   // }
}
