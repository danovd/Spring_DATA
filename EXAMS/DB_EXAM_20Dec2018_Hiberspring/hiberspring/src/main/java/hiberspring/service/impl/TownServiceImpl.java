package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.TownImportDTO;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = PATH_TO_FILES + "towns.json";
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
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return fileUtil.readFile(TOWNS_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        String json = this.readTownsJsonFile();

        TownImportDTO[] importDTOs = this.gson.fromJson(json, TownImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(TownImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);
        // За да не правим допълнителната проверка за getPopulation, може просто самото поле в DTO-то
        // да е вместо int -> Integer. Това гарантира NotNull, чрез анотацията. Но на примитивен тип няма null
        if (!isValid || dto.getPopulation() <= 0) {
            return INCORRECT_DATA_MESSAGE;
        }

        Optional<Town> optTown = this.townRepository.findByNameAndPopulation(dto.getName(), dto.getPopulation());


        if (optTown.isPresent()) {
            return INCORRECT_DATA_MESSAGE;
        }

        Town town = this.modelMapper.map(dto, Town.class);


        this.townRepository.save(town);

        return String.format(SUCCESSFUL_IMPORT_MESSAGE, "Town", dto.getName());
    }
}
