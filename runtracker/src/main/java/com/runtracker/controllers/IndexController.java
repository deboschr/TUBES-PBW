package com.runtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

   // Render halaman home
   @GetMapping("/")
   public String halaman() {
      return "redirect:/home";
   }

   // Render halaman home
   @GetMapping("/home")
   public String renderHomePage(Model model) {
      model.addAttribute("title", "Home"); // Judul halaman
      model.addAttribute("page", "home"); // Nama halaman
      return "index";
   }

   // Render halaman dashboard member
   @GetMapping("/dashboard")
   public String renderDashboardPage(Model model) {
      model.addAttribute("title", "Dashboard"); // Judul halaman
      model.addAttribute("page", "dashboard"); // Nama halaman
      return "index";
   }
}
