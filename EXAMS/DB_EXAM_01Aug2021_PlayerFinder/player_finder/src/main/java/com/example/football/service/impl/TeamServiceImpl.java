package com.example.football.service.impl;

import com.example.football.models.dto.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.FileUtil;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String TEAMS_FILE_PATH = "src/main/resources/files/json/teams.json";
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;
    private final TownRepository townRepository;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return fileUtil.readFile(TEAMS_FILE_PATH);
    }

    @Override
    public String importTeams() throws IOException {

        String json = this.readTeamsFileContent();

        TeamImportDTO[] importDTOs = this.gson.fromJson(json, TeamImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(TeamImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);
        if(!isValid){
            return "Invalid Team";
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getName());


        if (optTeam.isPresent()) {
            return "Invalid Team";
        }

        Team team = this.modelMapper.map(dto, Team.class);


        Optional<Town> town = this.townRepository.findByName(dto.getTown());
        ///  There is a condition that given town is always valid. If it wasn't, just one if construction
        ///  to check it and if not valid - return the appropriate message.
      //  if(town.isEmpty()){return "Invalid Team";}
        team.setTown(town.get());

        this.teamRepository.save(team);

        return String.format("Successfully imported Team %s - %d", team.getName(), team.getFanBase());
    }
}
