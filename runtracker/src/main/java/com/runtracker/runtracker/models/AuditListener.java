package com.runtracker.models;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

public class AuditListener {

   @PrePersist
   public void setCreatedAt(Object entity) {
      if (entity instanceof BaseEntity) {
         ((BaseEntity) entity).setCreatedAt(Instant.now().toEpochMilli());
      }
   }

   @PreUpdate
   public void setUpdatedAt(Object entity) {
      if (entity instanceof BaseEntity) {
         ((BaseEntity) entity).setUpdatedAt(Instant.now().toEpochMilli());
      }
   }
}
