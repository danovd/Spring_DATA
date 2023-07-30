package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportAstronomerDto;
import softuni.exam.models.dto.ImportAstronomerRootDTO;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AstronomerServiceImpl implements AstronomerService {


    private static final String ASTRONOMERS_FILE_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;
    private final StarRepository starRepository;

    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.starRepository = starRepository;
    }

    @Override
    public boolean areImported() {

        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {

        return Files.readString(Path.of(ASTRONOMERS_FILE_PATH));
        //  return fileUtil.readFile(ASTRONOMERS_FILE_PATH);
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        ImportAstronomerRootDTO statRootDTOs = this.xmlParser.parseXml(ASTRONOMERS_FILE_PATH, ImportAstronomerRootDTO.class);
        return statRootDTOs.getAstronomers().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportAstronomerDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid astronomer";
        }

        Optional<Astronomer> optAstronomer = this.astronomerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());


        if (optAstronomer.isPresent()) {
            return "Invalid astronomer";
        }


        Astronomer astronomer = this.modelMapper.map(dto, Astronomer.class);

        // Find and set a star
        Optional<Star> star = this.starRepository.findById(dto.getObservingStar());

        if(star.isEmpty()){
            return "Invalid astronomer";
        }
        astronomer.setObservingStar(star.get());
        ///


        this.astronomerRepository.save(astronomer);

        return String.format("Successfully imported astronomer %s %s - %.2f",
                dto.getFirstName(), dto.getLastName(), dto.getAverageObservationHours());
    }
}

