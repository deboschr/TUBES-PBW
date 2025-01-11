package com.runtracker.services;

import com.runtracker.models.Activity;
import com.runtracker.models.User;
import com.runtracker.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

   @Autowired
   private ActivityRepository activityRepository;

   public List<Activity> getActivitiesByUser(User user) {
      return activityRepository.findByPengguna(user);
   }

   public Activity createActivity(Activity activity) {
      return activityRepository.save(activity);
   }

   public Activity updateActivity(Long id, Activity updatedActivity) {
      Activity existingActivity = activityRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
      existingActivity.setTitle(updatedActivity.getTitle());
      existingActivity.setDescription(updatedActivity.getDescription());
      existingActivity.setDuration(updatedActivity.getDuration());
      existingActivity.setDistance(updatedActivity.getDistance());
      existingActivity.setUpdatedAt(System.currentTimeMillis());
      return activityRepository.save(existingActivity);
   }

   public void deleteActivity(Long id) {
      if (!activityRepository.existsById(id)) {
         throw new IllegalArgumentException("Activity not found");
      }
      activityRepository.deleteById(id);
   }
}
