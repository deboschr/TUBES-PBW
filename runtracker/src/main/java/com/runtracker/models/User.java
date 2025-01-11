package com.runtracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "pengguna")
@Data
@NoArgsConstructor
@EntityListeners(AuditListener.class)
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

   @Column(name = "created_at")
   private Long createdAt;

   @Column(name = "updated_at")
   private Long updatedAt;

   @OneToMany(mappedBy = "pengguna", cascade = CascadeType.ALL)
   private Set<Activity> activities;

   @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
   private Set<Race> createdRaces;

   @OneToMany(mappedBy = "updator", cascade = CascadeType.ALL)
   private Set<Race> updatedRaces;

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private Set<MemberRace> memberRaces;

   public enum Role {
      MEMBER, ADMIN
   }
}
