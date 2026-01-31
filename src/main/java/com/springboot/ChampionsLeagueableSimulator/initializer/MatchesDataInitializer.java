package com.springboot.ChampionsLeagueableSimulator.initializer;

import com.springboot.ChampionsLeagueableSimulator.dtos.MatchDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.MatchesFileDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.RoundDTO;
import com.springboot.ChampionsLeagueableSimulator.entity.Match;
import com.springboot.ChampionsLeagueableSimulator.entity.Team;
import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import com.springboot.ChampionsLeagueableSimulator.exceptions.TeamNotFoundException;
import com.springboot.ChampionsLeagueableSimulator.repositories.MatchRepository;
import com.springboot.ChampionsLeagueableSimulator.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MatchesDataInitializer implements CommandLineRunner {

    private final MatchRepository matchRepository;
    private final ObjectMapper objectMapper;
    private final TeamRepository teamRepository;


    @Override
    public void run(String... args) throws Exception {

        initializeMatches();
    }

    private void initializeMatches() {

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("matches.json");

        MatchesFileDTO matchesFileDTO = objectMapper.readValue(inputStream , MatchesFileDTO.class);

        for (RoundDTO round : matchesFileDTO.getRounds()) {

            int roundNumber = round.getRoundNumber();

            for (MatchDTO match : round.getMatches()) {

                Team homeTeam = teamRepository.findByName(match.getHomeTeam())
                        .orElseThrow(() -> new TeamNotFoundException("Team not found for team Name : "+match.getHomeTeam()));
                Team awayTeam = teamRepository.findByName(match.getAwayTeam())
                        .orElseThrow(() -> new TeamNotFoundException("Team not found for team name : "+match.getAwayTeam()));

                Match currentMatch = Match.builder()
                        .homeTeamId(homeTeam.getId())
                        .awayTeamId(awayTeam.getId())
                        .homeGoals(0)
                        .awayGoals(0)
                        .status(Status.SCHEDULED)
                        .roundNumber(roundNumber)
                        .build();

                matchRepository.save(currentMatch);
            }

        }

    }
}
