package com.runtracker.controllers;

import com.runtracker.models.Activity;
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
public class ActivityController {

   @Autowired
   private UserService userService;

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   // Render halaman activity member
   @GetMapping("/activity")
   public String renderActivityPage(HttpSession session, Model model) {
      User user = (User) session.getAttribute("dataSession");

      if (user == null) {
        return "redirect:/user/signin";
      }

      model.addAttribute("title", "Activity"); // Judul halaman
      model.addAttribute("page", "activity"); // Nama halaman
      model.addAttribute("user", user); // Data pengguna

      return "index";
   }
}