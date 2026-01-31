package com.springboot.ChampionsLeagueableSimulator.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoreDTO {

    private Integer homeGoal;
    private Integer awayGoal;
}
