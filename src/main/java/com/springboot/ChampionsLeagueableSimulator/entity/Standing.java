package com.springboot.ChampionsLeagueableSimulator.entity;

import jakarta.persistence.*;

@Entity
public class Standing {

    @Id
    private Long teamId;
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
