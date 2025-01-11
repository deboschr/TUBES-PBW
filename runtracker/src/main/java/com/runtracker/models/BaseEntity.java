package com.runtracker.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

   @Column(name = "created_at", nullable = false, updatable = false)
   private Long createdAt;

   @Column(name = "updated_at")
   private Long updatedAt;
}
