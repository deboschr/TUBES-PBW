package com.runtracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "pengguna")
@Data
@NoArgsConstructor
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "pengguna_id")
   private Long id;

   @Column(nullable = false, length = 200)
   private String name;

   @Column(nullable = false, unique = true, length = 200)
   private String email;

   @Column(nullable = false)
   private String password;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false, length = 50)
   private Role role;

   @Column(name = "created_at", nullable = false, updatable = false)
   private Long createdAt;

   @Column(name = "updated_at")
   private Long updatedAt;

   @OneToMany(mappedBy = "pengguna", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<Activity> activities;

   @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<Race> createdRaces;

   @OneToMany(mappedBy = "updator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<Race> updatedRaces;

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private Set<MemberRace> memberRaces;

   public enum Role {
      MEMBER, ADMIN
   }

   @PrePersist
   public void prePersist() {
      this.createdAt = System.currentTimeMillis();
   }

   @PreUpdate
   public void preUpdate() {
      this.updatedAt = System.currentTimeMillis();
   }
}
