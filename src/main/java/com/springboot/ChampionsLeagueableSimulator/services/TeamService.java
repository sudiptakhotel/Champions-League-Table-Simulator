package com.springboot.ChampionsLeagueableSimulator.services;

import com.springboot.ChampionsLeagueableSimulator.dtos.TeamDTO;

import java.util.List;

public interface TeamService {

    public List<TeamDTO> getTeams();
    public TeamDTO getTeamById(Long teamId);
}
