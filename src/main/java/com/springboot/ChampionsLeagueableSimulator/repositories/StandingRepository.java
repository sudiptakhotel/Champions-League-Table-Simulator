package com.springboot.ChampionsLeagueableSimulator.repositories;

import com.springboot.ChampionsLeagueableSimulator.entity.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandingRepository extends JpaRepository<Standing , Long> {
}
