package com.runtracker.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class MemberRaceId implements Serializable {
   private Long member;
   private Long race;
}
