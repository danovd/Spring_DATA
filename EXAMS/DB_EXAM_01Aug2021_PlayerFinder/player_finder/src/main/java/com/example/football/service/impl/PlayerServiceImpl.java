package com.example.football.service.impl;

import com.example.football.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {


    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPlayersFileContent()  {
        return null;
    }

    @Override
    public String importPlayers()  {
        return null;
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}
