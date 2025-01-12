package com.runtracker.controllers;

import com.runtracker.models.Activity;
import com.runtracker.models.User;
// import com.runtracker.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class ActivityController {

   @Autowired
   // private ActivityService activityService;

   // Render halaman activity
   @GetMapping("/activity")
   public String renderActivityPage(HttpSession session, Model model) {
      User user = (User) session.getAttribute("dataSession");

      if (user == null) {
         return "redirect:/user/signin";
      }

      // List<Activity> activities = activityService.getActivitiesByUser(user);


      model.addAttribute("title", "Activity");
      model.addAttribute("page", "activity");
      model.addAttribute("user", user);
      // model.addAttribute("activities", activities);

      return "index";
   }

   // // Create new activity
   // @PostMapping("/activity")
   // public String createActivity(@ModelAttribute Activity activity, HttpSession session) {
   //    User user = (User) session.getAttribute("dataSession");

   //    if (user == null) {
   //       return "redirect:/user/signin";
   //    }

   //    activity.setPengguna(user);
   //    activity.setCreatedAt(System.currentTimeMillis());
   //    activity.setUpdatedAt(System.currentTimeMillis());
   //    activityService.createActivity(activity);

   //    return "redirect:/activity";
   // }

   // // Update activity
   // @PatchMapping("/activity/{id}")
   // public String updateActivity(@PathVariable Long id, @ModelAttribute Activity activity, HttpSession session) {
   //    User user = (User) session.getAttribute("dataSession");

   //    if (user == null) {
   //       return "redirect:/user/signin";
   //    }

   //    activityService.updateActivity(id, activity);

   //    return "redirect:/activity";
   // }

   // // Delete activity
   // @DeleteMapping("/activity/{id}")
   // public String deleteActivity(@PathVariable Long id, HttpSession session) {
   //    User user = (User) session.getAttribute("dataSession");

   //    if (user == null) {
   //       return "redirect:/user/signin";
   //    }

   //    activityService.deleteActivity(id);

   //    return "redirect:/activity";
   // }
}
