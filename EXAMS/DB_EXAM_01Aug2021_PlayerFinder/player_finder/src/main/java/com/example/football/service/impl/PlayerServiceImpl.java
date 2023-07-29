package com.example.football.service.impl;

import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.FileUtil;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return fileUtil.readFile(PLAYERS_FILE_PATH);
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
