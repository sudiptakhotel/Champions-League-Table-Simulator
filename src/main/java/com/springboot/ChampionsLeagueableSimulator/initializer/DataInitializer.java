package com.springboot.ChampionsLeagueableSimulator.initializer;

import com.springboot.ChampionsLeagueableSimulator.entity.Standing;
import com.springboot.ChampionsLeagueableSimulator.entity.Team;
import com.springboot.ChampionsLeagueableSimulator.repositories.StandingRepository;
import com.springboot.ChampionsLeagueableSimulator.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final StandingRepository standingRepository;

    //this object mapper converts JSON -> Java Object and vice versa
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {
        initializeTeams();
    }

    private void initializeTeams() {

        //checking if the Team data is initialized already
        if (teamRepository.count() > 0)
            return;

        try (InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("teams.json")) {

            List<String> teamNames = objectMapper.readValue(inputStream, new TypeReference<List<String>>() {
            });

            for (String name : teamNames) {

                Team team = Team.builder()
                        .name(name)
                        .build();

                Team savedTeam = teamRepository.save(team);

                //create entry for standing

                Standing standing = Standing.builder()
                        .teamId(savedTeam.getId())
                        .teamName(savedTeam.getName())
                        .wins(0)
                        .losses(0)
                        .draws(0)
                        .awayGoals(0)
                        .awayWins(0)
                        .played(0)
                        .goalDifference(0)
                        .goalScored(0)
                        .points(0)
                        .build();

                standingRepository.save(standing);
            }
        }catch (IOException e) {
            System.out.println("Exception");
        }
    }
}
