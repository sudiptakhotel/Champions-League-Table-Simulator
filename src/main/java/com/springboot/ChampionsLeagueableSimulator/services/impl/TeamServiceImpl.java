package com.springboot.ChampionsLeagueableSimulator.services.impl;

import com.springboot.ChampionsLeagueableSimulator.configurations.ModelMapperConfig;
import com.springboot.ChampionsLeagueableSimulator.dtos.TeamDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.TeamStandingDTO;
import com.springboot.ChampionsLeagueableSimulator.entity.Standing;
import com.springboot.ChampionsLeagueableSimulator.entity.Team;
import com.springboot.ChampionsLeagueableSimulator.exceptions.TeamNotFoundException;
import com.springboot.ChampionsLeagueableSimulator.repositories.StandingRepository;
import com.springboot.ChampionsLeagueableSimulator.repositories.TeamRepository;
import com.springboot.ChampionsLeagueableSimulator.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final StandingRepository standingRepository;


    @Override
    public List<TeamDTO> getTeams() {

        List<Team> teams = teamRepository.findAll();

        List<TeamDTO> teamDTOS = new ArrayList<>();

        for (Team team : teams) {
            teamDTOS.add(modelMapper.map(team , TeamDTO.class));
        }
        return teamDTOS;
    }

    @Override
    public TeamDTO getTeamById(Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with given teamId"));

        return modelMapper.map(team , TeamDTO.class);
    }

    @Override
    public TeamStandingDTO getTeamPointsById(Long teamId) {

        Standing team = standingRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with given teamId"));

        TeamStandingDTO teamStandingDTO = TeamStandingDTO.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .played(team.getPlayed())
                .points(team.getPoints())
                .build();

        System.out.println(teamStandingDTO);

        return teamStandingDTO;
    }
}
