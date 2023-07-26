package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportPlaneDto;
import softuni.exam.models.dto.ImportPlaneRootDTO;
import softuni.exam.models.entity.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl implements PlaneService {

    private static final String PLANES_FILE_PATH = "src/main/resources/files/xml/planes.xml";
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return Files.readString(Path.of(PLANES_FILE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException {

        ImportPlaneRootDTO planeRootDTOs = this.xmlParser.fromFile(PLANES_FILE_PATH, ImportPlaneRootDTO.class);
        return planeRootDTOs.getPlanes().stream().map(this::importDTO).collect(Collectors.joining("\n"));

    }

    private String importDTO(ImportPlaneDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Plane";
        }

        Optional<Plane> optPlane = this.planeRepository.findByRegisterNumber(dto.getRegisterNumber());


        if (optPlane.isPresent()) {
            return "Invalid Plane";
        }


        Plane plane = this.modelMapper.map(dto, Plane.class);


        this.planeRepository.save(plane);

        return"Successfully imported Plane " + plane.getRegisterNumber();
    }
}
