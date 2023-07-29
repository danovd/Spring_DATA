package com.example.football.service;

import java.io.IOException;

//ToDo - Implement all methods
public interface PlayerService {
    boolean areImported();

    String readPlayersFileContent() throws IOException;

    String importPlayers() ;

    String exportBestPlayers();
}
