package com.springboot.ChampionsLeagueableSimulator.repositories;


import com.springboot.ChampionsLeagueableSimulator.dtos.MatchDTO;
import com.springboot.ChampionsLeagueableSimulator.entity.Match;
import com.springboot.ChampionsLeagueableSimulator.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match , Long> {

    List<Match> findByRoundNumber(int roundNumber);
    List<Match> findByRoundNumberAndStatus(Integer roundNumber , Status status);
}
