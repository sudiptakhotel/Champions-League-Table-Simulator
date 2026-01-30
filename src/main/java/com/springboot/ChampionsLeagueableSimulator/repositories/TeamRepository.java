package com.springboot.ChampionsLeagueableSimulator.repositories;

import com.springboot.ChampionsLeagueableSimulator.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team , Long> {
}
