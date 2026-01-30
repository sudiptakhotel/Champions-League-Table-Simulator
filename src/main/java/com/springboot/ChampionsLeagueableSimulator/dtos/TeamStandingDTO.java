package com.springboot.ChampionsLeagueableSimulator.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class TeamStandingDTO {

    private Long teamId;
    private String teamName;
    private Integer played;
    private Integer points;

}
