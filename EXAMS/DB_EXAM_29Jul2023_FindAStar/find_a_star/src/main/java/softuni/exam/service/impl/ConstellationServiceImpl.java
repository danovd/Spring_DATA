package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String CONSTELLATIONS_FILE_PATH = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil) {
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }


    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(CONSTELLATIONS_FILE_PATH));
        //return fileUtil.readFile(CONSTELLATIONS_FILE_PATH);
    }

    @Override
    public String importConstellations() throws IOException {
        String json = this.readConstellationsFromFile();

        ConstellationImportDTO[] importDTOs = this.gson.fromJson(json, ConstellationImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(ConstellationImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if(!isValid){
            return "Invalid constellation";
        }

        Optional<Constellation> optConstellation = this.constellationRepository.findByName(dto.getName());


        if (optConstellation.isPresent()) {
            return "Invalid constellation";
        }

        Constellation constellation = this.modelMapper.map(dto, Constellation.class);


        this.constellationRepository.save(constellation);

        return String.format("Successfully imported constellation %s - %s", constellation.getName(), constellation.getDescription());
    }
}
