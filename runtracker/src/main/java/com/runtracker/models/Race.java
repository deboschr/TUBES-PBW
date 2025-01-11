package com.runtracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "race")
@Data
@NoArgsConstructor
public class Race {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "race_id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "created_by", nullable = false)
   private User creator;

   @ManyToOne
   @JoinColumn(name = "updated_by")
   private User updator;

   @Column(nullable = false, length = 200)
   private String name;

   @Column(nullable = false)
   private String description;

   @Column(nullable = false, length = 50)
   private String status;

   @Column(name = "created_at", nullable = false, updatable = false)
   private Long createdAt;

   @Column(name = "updated_at")
   private Long updatedAt;

   @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<MemberRace> memberRaces;

   @PrePersist
   public void prePersist() {
      this.createdAt = System.currentTimeMillis();
   }

   @PreUpdate
   public void preUpdate() {
      this.updatedAt = System.currentTimeMillis();
   }
}
