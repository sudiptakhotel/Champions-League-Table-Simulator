package com.springboot.ChampionsLeagueableSimulator.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
