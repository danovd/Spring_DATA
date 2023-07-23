package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicImportDTO;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MechanicsServiceImpl implements MechanicsService {

private final MechanicsRepository mechanicsRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository) {
        this.mechanicsRepository = mechanicsRepository;
        this.gson = new GsonBuilder().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return this.mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "mechanics.json");

        return Files.readString(path);
    }

    @Override
    public String importMechanics() throws IOException {
        String json = this.readMechanicsFromFile();

        MechanicImportDTO[] importDTOs = this.gson.fromJson(json, MechanicImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(MechanicImportDTO dto) {
        Set<ConstraintViolation<MechanicImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid mechanic";
        }

        Optional<Mechanic> optMechanic = this.mechanicsRepository.findByEmail(dto.getEmail());

        if (optMechanic.isPresent()) {
            return "Invalid mechanic";
        }

        Mechanic mechanic = this.modelMapper.map(dto, Mechanic.class);

        this.mechanicsRepository.save(mechanic);

        return "Successfully imported mechanic " + mechanic.getFirstName() + " " + mechanic.getLastName();
    }
}
