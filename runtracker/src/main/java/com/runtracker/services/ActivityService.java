package com.runtracker.services;

import com.runtracker.dao.ActivityDAO;
import com.runtracker.models.Activity;
import com.runtracker.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

   @Autowired
   private ActivityDAO activityDAO;

   public ActivityService(ActivityDAO activityDAO) {
      this.activityDAO = activityDAO;
   }

   public List<Activity> getActivitiesByUser(User user) {
      return activityDAO.findByUser(user);
   }

   public void createActivity(Activity activity) {
      activity.setCreatedAt(System.currentTimeMillis());
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
