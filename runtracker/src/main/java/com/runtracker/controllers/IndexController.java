package com.runtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class IndexController {

   // Render halaman home
   @GetMapping("/")
   public String renderHomePage(Model model) {
      model.addAttribute("title", "Home"); // Judul halaman
      model.addAttribute("page", "homepage"); // Nama halaman
      return "index";
   }
}

