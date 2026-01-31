package com.springboot.ChampionsLeagueableSimulator.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {

    private String homeTeam;
    private String awayTeam;
}
