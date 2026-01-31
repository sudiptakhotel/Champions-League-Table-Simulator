package com.springboot.ChampionsLeagueableSimulator.controllers;

import com.springboot.ChampionsLeagueableSimulator.dtos.MatchWithScoreDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.RoundDTO;
import com.springboot.ChampionsLeagueableSimulator.dtos.ScoreDTO;
import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import com.springboot.ChampionsLeagueableSimulator.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping(path = "/match/getMatchesByRound/{roundNumber}")
    public ResponseEntity<RoundDTO> getMatchesByRound(@PathVariable Integer roundNumber) {

        RoundDTO roundDTO = matchService.getAllMatchesByRound(roundNumber);
        return ResponseEntity.ok(roundDTO);

    }

    @GetMapping(path = "/match/getMatchesByRoundAndStatus/{roundNumber}/{status}")
    public ResponseEntity<RoundDTO> getMatchesByRoundAndStatus(@PathVariable Integer roundNumber ,
                                                               @PathVariable String status) {
        RoundDTO roundDTO = matchService.getAllMatchesByRoundNumberAndStatus(roundNumber , Status.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(roundDTO);
    }

    @PostMapping(path = "/match/updateScore/{matchId}")
    public ResponseEntity<MatchWithScoreDTO> updateScore(@PathVariable Long matchId ,
                                                         @RequestBody ScoreDTO scoreDTO) {

        MatchWithScoreDTO match = matchService.updateScore(matchId , scoreDTO);

        return ResponseEntity.ok(match);
    }
    @PostMapping(path = "/match/finalWhistle/{matchId}")
    public ResponseEntity<MatchWithScoreDTO> finalWhistle(@PathVariable Long matchId) {

        MatchWithScoreDTO match = matchService.finalWhistle(matchId);

        return ResponseEntity.ok(match);
    }
}
