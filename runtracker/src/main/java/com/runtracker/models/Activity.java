package com.runtracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity")
@Data
@NoArgsConstructor
public class Activity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "activity_id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "pengguna_id", nullable = false)
   private User pengguna;

   @Column(nullable = false, length = 100)
   private String title;

   @Column(nullable = false)
   private String description;

   @Column(nullable = false)
   private Double duration;

   @Column(nullable = false)
   private Double distance;

   @Column(name = "created_at", nullable = false, updatable = false)
   private Long createdAt;

   @Column(name = "updated_at")
   private Long updatedAt;

   @OneToOne(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private MemberRace memberRace;

   @PrePersist
   public void prePersist() {
      this.createdAt = System.currentTimeMillis();
   }

   @PreUpdate
   public void preUpdate() {
      this.updatedAt = System.currentTimeMillis();
   }
}
