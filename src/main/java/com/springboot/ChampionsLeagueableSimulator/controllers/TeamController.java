package com.springboot.ChampionsLeagueableSimulator.controllers;

import com.springboot.ChampionsLeagueableSimulator.dtos.TeamDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.TeamStandingDTO;
import com.springboot.ChampionsLeagueableSimulator.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping(path = "/team/getTeamById/{teamId}")
    public ResponseEntity<TeamDTO> getTeamByTeamId(@PathVariable Long teamId) {

        return ResponseEntity.ok(teamService.getTeamById(teamId));
    }

    @GetMapping(path = "/team/getAllTeams")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {

        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping(path = "/team/{teamId}/standing")
    public ResponseEntity<TeamStandingDTO> getTeamStanding(@PathVariable Long teamId) {

        TeamStandingDTO teamStanding = teamService.getTeamPointsById(teamId);
        return ResponseEntity.ok(teamStanding);
    }

}
