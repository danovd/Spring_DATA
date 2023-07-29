package com.example.football.service.impl;

import com.example.football.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {


    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readTeamsFileContent() {
        return null;
    }

    @Override
    public String importTeams() {
        return null;
    }
}
