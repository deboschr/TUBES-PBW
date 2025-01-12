package com.runtracker.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Activity {
   private Long id;
   private User pengguna;
   private String title;
   private String description;
   private Double duration;
   private Double distance;
   private String type;
   private Long createdAt;
   private Long updatedAt;

   private MemberRace memberRace;
}
