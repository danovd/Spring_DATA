package com.example.football.service;

//ToDo - Implement all methods
public interface PlayerService {
    boolean areImported();

    String readPlayersFileContent() ;

    String importPlayers() ;

    String exportBestPlayers();
}
