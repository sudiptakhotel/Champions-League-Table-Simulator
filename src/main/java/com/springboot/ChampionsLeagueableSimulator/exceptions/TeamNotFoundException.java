package com.springboot.ChampionsLeagueableSimulator.exceptions;


public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(String message) {
        super(message);
    }
}
