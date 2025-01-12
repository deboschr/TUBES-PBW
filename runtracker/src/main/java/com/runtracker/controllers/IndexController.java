package com.runtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

<<<<<<< HEAD
   @RequestMapping("/upload")
   public String index() {
      return "upload";
   }

   @PostMapping("/upload")
   public String singleFileUpload(@RequestParam("file") MultipartFile file,
         RedirectAttributes redirectAttributes) {

      if (file.isEmpty()) {
         redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
         return "redirect:uploadStatus";
      }

      try {
         byte[] bytes = file.getBytes();
         Path path = Paths.get("src/main/resources/static/image/" + file.getOriginalFilename());
         Files.write(path, bytes);

         redirectAttributes.addFlashAttribute("message",
               "You successfully uploaded '" + file.getOriginalFilename() + "'");

      } catch (IOException e) {
         e.printStackTrace();
      }

      return "redirect:/uploadStatus";
   }

   @RequestMapping("/uploadStatus")
   public String uploadStatus() {
      return "uploadStatus";
   }

=======
>>>>>>> parent of 50bc43b (ada)
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
