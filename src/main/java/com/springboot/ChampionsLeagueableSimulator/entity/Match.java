package com.springboot.ChampionsLeagueableSimulator.entity;

import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long homeTeamId;
    private Long awayTeamId;

    private Integer homeGoals;
    private Integer awayGoals;

    private Integer roundNumber;

    @Enumerated(EnumType.STRING)
    private Status status;
}
