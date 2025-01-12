package com.runtracker.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Race {
   private Long id;
   private User creator;
   private User updator;
   private String name;
   private String description;
   private String status;
   private Long createdAt;
   private Long updatedAt;

   private Set<MemberRace> memberRaces;
}
