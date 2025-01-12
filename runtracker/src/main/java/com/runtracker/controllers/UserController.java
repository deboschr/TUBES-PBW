package com.runtracker.controllers;

import com.runtracker.models.User;
import com.runtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService userService;

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

   // Render halaman profile
   @GetMapping("/profile")
   public String renderProfilePage(HttpSession session, Model model) {
      User user = (User) session.getAttribute("dataSession");

      if (user == null) {
         return "redirect:/user/signin";
      }

      model.addAttribute("title", "User Profile"); // Judul halaman
      model.addAttribute("page", "profile"); // Nama halaman
      model.addAttribute("user", user); // Data pengguna

      return "index";
   }

   // Handle signin
   @PostMapping("/signin")
   public String signin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
      try {
         // Proses signin melalui service
         User user = userService.authenticate(email, password);

         System.out.println("USER :" + user);
         System.out.println("USER ACTIVITIES" + user.getActivities());

         // Simpan pengguna yang berhasil login ke session
         session.setAttribute("dataSession", user);

         // Redirect ke halaman profile setelah berhasil signin
         return "redirect:/dashboard";
      } catch (IllegalArgumentException e) {
         // Tangani error dari service dan tampilkan pesan ke pengguna
         model.addAttribute("error", e.getMessage());
         return "signin"; // Kembali ke halaman signin dengan pesan error
      }
   }

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
