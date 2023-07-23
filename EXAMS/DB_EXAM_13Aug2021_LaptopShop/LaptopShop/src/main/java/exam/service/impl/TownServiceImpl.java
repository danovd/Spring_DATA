package exam.service.impl;

import exam.model.dto.ImportTownDto;
import exam.model.dto.ImportTownRootDTO;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_FILE_PATH = "src/main/resources/files/xml/towns.xml";
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        ImportTownRootDTO townRootDTOs = this.xmlParser.fromFile(TOWNS_FILE_PATH, ImportTownRootDTO.class);
        return townRootDTOs.getTowns().stream().map(this::importTown).collect(Collectors.joining("\n"));
    }

    private String importTown(ImportTownDto dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid town";
        }

        Optional<Town> optTown = this.townRepository.findByName(dto.getName());


        if (optTown.isPresent()) {
            return "Invalid town";
        }


        Town town = this.modelMapper.map(dto, Town.class);


        this.townRepository.save(town);

        return"Successfully imported Town " + town.getName();
    }
}
