package com.springboot.ChampionsLeagueableSimulator.dtos;

import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchWithScoreDTO {

    private String homeTeam;
    private String awayTeam;
    private Integer homeGoal;
    private Integer awayGoals;
    private Status status;
}
