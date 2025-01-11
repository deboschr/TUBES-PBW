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
   //    User loggedInUser = (User) session.getAttribute("loggedInUser");
   //    if (loggedInUser == null) {
   //       return "redirect:/user/signin";
   //    }
   //    model.addAttribute("user", loggedInUser);
   //    return "profile";
   // }

   // // Handle signup
   // @PostMapping("/signup")
   // public String signup(@ModelAttribute User user, Model model) {
   //    if (userService.getUserByEmail(user.getEmail()).isPresent()) {
   //       model.addAttribute("error", "Email is already in use.");
   //       return "signup";
   //    }
   //    userService.saveUser(user);
   //    return "redirect:/user/signin";
   // }

   // // Handle signin
   // @PostMapping("/signin")
   // public String signin(@RequestParam String email, @RequestParam String password, HttpSession session,
   //       Model model) {
   //    User user = userService.getUserByEmail(email).orElse(null);
   //    if (user == null || !user.getPassword().equals(password)) {
   //       model.addAttribute("error", "Invalid email or password.");
   //       return "signin";
   //    }
   //    session.setAttribute("loggedInUser", user);
   //    return "redirect:/user/profile";
   // }

   // // Handle signout
   // @PostMapping("/signout")
   // public String signout(HttpSession session) {
   //    session.invalidate();
   //    return "redirect:/user/signin";
   // }

   // // Handle update profile
   // @PutMapping("/profile")
   // public ResponseEntity<String> update(@ModelAttribute User userDetails, HttpSession session) {
   //    User loggedInUser = (User) session.getAttribute("loggedInUser");
   //    if (loggedInUser == null) {
   //       return ResponseEntity.status(403).body("Unauthorized");
   //    }
   //    loggedInUser.setName(userDetails.getName());
   //    loggedInUser.setEmail(userDetails.getEmail());
   //    if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
   //       loggedInUser.setPassword(userDetails.getPassword());
   //    }
   //    userService.saveUser(loggedInUser);
   //    return ResponseEntity.ok("Profile updated successfully");
   // }

   // // Handle delete account
   // @DeleteMapping("/profile")
   // public ResponseEntity<String> delete(HttpSession session) {
   //    User loggedInUser = (User) session.getAttribute("loggedInUser");
   //    if (loggedInUser == null) {
   //       return ResponseEntity.status(403).body("Unauthorized");
   //    }
   //    userService.deleteUser(loggedInUser.getId());
   //    session.invalidate();
   //    return ResponseEntity.ok("Account deleted successfully");
   // }
}
