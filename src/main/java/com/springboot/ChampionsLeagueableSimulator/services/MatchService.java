package com.springboot.ChampionsLeagueableSimulator.services;

import com.springboot.ChampionsLeagueableSimulator.dtos.MatchDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.MatchWithScoreDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.RoundDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.ScoreDTO;
import com.springboot.ChampionsLeagueableSimulator.enums.Status;

import java.util.List;

public interface MatchService {

    public RoundDTO getAllMatchesByRound(int roundNumber);
    public RoundDTO getAllMatchesByRoundNumberAndStatus(Integer roundNumber , Status status);

    public MatchWithScoreDTO startMatch(Long matchId);
    public MatchWithScoreDTO updateScore(Long matchId , ScoreDTO scoreDTO);
    public MatchWithScoreDTO finalWhistle(Long matchId);


}
