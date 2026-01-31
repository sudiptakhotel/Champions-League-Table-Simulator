package com.springboot.ChampionsLeagueableSimulator.services.impl;

import com.springboot.ChampionsLeagueableSimulator.dtos.MatchDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.MatchWithScoreDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.RoundDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.ScoreDTO;
import com.springboot.ChampionsLeagueableSimulator.entity.Match;
import com.springboot.ChampionsLeagueableSimulator.entity.Team;
import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import com.springboot.ChampionsLeagueableSimulator.exceptions.MatchNotFoundException;
import com.springboot.ChampionsLeagueableSimulator.exceptions.TeamNotFoundException;
import com.springboot.ChampionsLeagueableSimulator.repositories.MatchRepository;
import com.springboot.ChampionsLeagueableSimulator.repositories.TeamRepository;
import com.springboot.ChampionsLeagueableSimulator.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Score;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;


    @Override
    public RoundDTO getAllMatchesByRound(int roundNumber) {

        List<Match> matches = matchRepository.findByRoundNumber(roundNumber);

        if (matches.isEmpty())
            throw new MatchNotFoundException("Matches not found with the given round number : "+roundNumber);

        List<MatchDTO> matchDTOS = new ArrayList<>();
        for (Match match : matches) {

            Team homeTeam = teamRepository.findById(match.getHomeTeamId())
                    .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getHomeTeamId()));

            Team awayTeam = teamRepository.findById(match.getAwayTeamId())
                    .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getAwayTeamId()));

            MatchDTO matchDTO = MatchDTO.builder()
                    .homeTeam(homeTeam.getName())
                    .awayTeam(awayTeam.getName())
                    .build();

            matchDTOS.add(matchDTO);

        }

        return RoundDTO.builder()
                .matches(matchDTOS)
                .roundNumber(roundNumber)
                .build();

    }

    @Override
    public RoundDTO getAllMatchesByRoundNumberAndStatus(Integer roundNumber , Status status) {

        List<Match> matches = matchRepository.findByRoundNumberAndStatus(roundNumber , status);

        if (matches.isEmpty())
            throw new MatchNotFoundException("Matches not found with the given round number : "+roundNumber);

        List<MatchDTO> matchDTOS = new ArrayList<>();

        for (Match match : matches) {

            Team homeTeam = teamRepository.findById(match.getHomeTeamId())
                    .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getHomeTeamId()));

            Team awayTeam = teamRepository.findById(match.getAwayTeamId())
                    .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getAwayTeamId()));

            MatchDTO matchDTO = MatchDTO.builder()
                    .homeTeam(homeTeam.getName())
                    .awayTeam(awayTeam.getName())
                    .build();

            matchDTOS.add(matchDTO);

        }

        return RoundDTO.builder()
                .matches(matchDTOS)
                .roundNumber(roundNumber)
                .build();

   }

    @Override
    public MatchWithScoreDTO startMatch(Long matchId) {

        Match match = getMatchById(matchId);

        if (match.getStatus().equals(Status.LIVE) || match.getStatus().equals(Status.FINISHED))
            throw new RuntimeException("Match can only be started if it is not LIVE or FINISHED");

        match.setStatus(Status.LIVE);

        Match savedMatch = matchRepository.save(match);

        Team homeTeam = teamRepository.findById(match.getHomeTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getHomeTeamId()));

        Team awayTeam = teamRepository.findById(match.getAwayTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getAwayTeamId()));

        return MatchWithScoreDTO.builder()
                .homeTeam(homeTeam.getName())
                .awayTeam(awayTeam.getName())
                .homeGoal(0)
                .awayGoals(0)
                .build();
    }

    @Override
    public MatchWithScoreDTO updateScore(Long matchId , ScoreDTO scoreDTO) {

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with given id : "+matchId));

        if (!match.getStatus().equals(Status.LIVE))
            throw new RuntimeException("Match is not live now");

        match.setHomeGoals(scoreDTO.getHomeGoal());
        match.setAwayGoals(scoreDTO.getAwayGoal());

        Match savedMatch = matchRepository.save(match);

        Team homeTeam = teamRepository.findById(match.getHomeTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getHomeTeamId()));

        Team awayTeam = teamRepository.findById(match.getAwayTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getAwayTeamId()));


        return MatchWithScoreDTO.builder()
                .homeTeam(homeTeam.getName())
                .awayTeam(awayTeam.getName())
                .homeGoal(savedMatch.getHomeGoals())
                .awayGoals(savedMatch.getAwayGoals())
                .status(savedMatch.getStatus())
                .build();
    }


    @Override
    public MatchWithScoreDTO finalWhistle(Long matchId) {

        Match match = getMatchById(matchId);

        if (!match.getStatus().equals(Status.LIVE))
            throw new RuntimeException("Match is not live");

        match.setStatus(Status.FINISHED);

        Match savedMatch = matchRepository.save(match);

        Team homeTeam = teamRepository.findById(match.getHomeTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getHomeTeamId()));

        Team awayTeam = teamRepository.findById(match.getAwayTeamId())
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id : "+match.getAwayTeamId()));

        return MatchWithScoreDTO.builder()
                .homeTeam(homeTeam.getName())
                .awayTeam(awayTeam.getName())
                .homeGoal(savedMatch.getHomeGoals())
                .awayGoals(savedMatch.getAwayGoals())
                .status(savedMatch.getStatus())
                .build();
    }

    private Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found with given id : "+matchId));
    }
}
