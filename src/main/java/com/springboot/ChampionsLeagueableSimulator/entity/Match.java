package com.springboot.ChampionsLeagueableSimulator.entity;

import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import jakarta.persistence.*;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long homeTeamId;
    private Long awayTeamId;

    private Integer homeGoals;
    private Integer awayGoals;

    @Enumerated(EnumType.STRING)
    private Status status;
}
