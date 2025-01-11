package com.runtracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_race")
@Data
@NoArgsConstructor
@IdClass(MemberRaceId.class) // Composite primary key
@EntityListeners(AuditListener.class)
public class MemberRace {

   @Id
   @ManyToOne
   @JoinColumn(name = "member_id", nullable = false)
   private User member;

   @Id
   @ManyToOne
   @JoinColumn(name = "race_id", nullable = false)
   private Race race;

   @ManyToOne
   @JoinColumn(name = "activity_id")
   private Activity activity;

   @Column(name = "created_at")
   private Long createdAt;

   @Column(name = "updated_at")
   private Long updatedAt;
}
