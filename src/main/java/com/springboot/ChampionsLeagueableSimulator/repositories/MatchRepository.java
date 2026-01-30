package com.springboot.ChampionsLeagueableSimulator.repositories;

import com.springboot.ChampionsLeagueableSimulator.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match , Long> {
}
