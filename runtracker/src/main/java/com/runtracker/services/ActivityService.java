package com.runtracker.services;

import com.runtracker.dao.ActivityDAO;
import com.runtracker.models.Activity;
import com.runtracker.models.User;
import com.runtracker.utils.FileStorageUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

   @Autowired
   private ActivityDAO activityDAO;

   @Autowired
   private FileStorageUtils fileStorageUtils;

   public List<Activity> getActivitiesByUser(User user) {
      List<Activity> activities = activityDAO.findByUser(user);

      // Pastikan semua aktivitas memiliki tipe yang benar
      for (Activity activity : activities) {
         if (activity.getType() == null || activity.getType().isEmpty()) {
            activity.setType("Exercise");
         }
      }

      return activities;
   }

   public Activity getActivityDetail(long id) {
      return activityDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Activity not found"));
   }

   public void createActivity(Activity activity, MultipartFile file) {
      if (file != null && !file.isEmpty()) {
         try {
            String fileName = fileStorageUtils.storeFile(file);
            activity.setImage(fileName);
         } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
         }
      }
      activityDAO.save(activity);
   }

   public void updateActivity(Long id, Activity updatedActivity) {
      Optional<Activity> existingActivityOptional = activityDAO.findById(id);

      if (!existingActivityOptional.isPresent()) {
         throw new IllegalArgumentException("Activity not found");
      }

      Activity existingActivity = existingActivityOptional.get();

      existingActivity.setTitle(updatedActivity.getTitle());
      existingActivity.setDescription(updatedActivity.getDescription());
      existingActivity.setDuration(updatedActivity.getDuration());
      existingActivity.setDistance(updatedActivity.getDistance());
      existingActivity.setUpdatedAt(System.currentTimeMillis());

      activityDAO.update(existingActivity);
   }

   public void deleteActivity(Long id) {
      if (!activityDAO.findById(id).isPresent()) {
         throw new IllegalArgumentException("Activity not found");
      }
      activityDAO.delete(id);
   }

}
