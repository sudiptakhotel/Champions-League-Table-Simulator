package com.springboot.ChampionsLeagueableSimulator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Standing {

    @Id
    private Long teamId;
    private String teamName;
    private Integer played;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private Integer points;
    private Integer goalDifference;
    private Integer goalScored;
    private Integer awayGoals;
    private Integer awayWins;

}
