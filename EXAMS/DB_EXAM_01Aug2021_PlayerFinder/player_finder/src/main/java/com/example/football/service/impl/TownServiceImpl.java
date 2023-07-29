package com.example.football.service.impl;

import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.FileUtil;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return fileUtil.readFile(TOWNS_FILE_PATH);
    }

    @Override
    public String importTowns() {
        return null;
    }
}
