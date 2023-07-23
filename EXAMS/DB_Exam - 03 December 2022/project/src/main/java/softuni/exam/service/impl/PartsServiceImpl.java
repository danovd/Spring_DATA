package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartImportDTO;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;

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
public class PartsServiceImpl implements PartsService {


    private final PartsRepository partsRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;



    public PartsServiceImpl(PartsRepository partsRepository) {
        this.partsRepository = partsRepository;

        this.gson = new GsonBuilder().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();


    }

    @Override
    public boolean areImported() {
        return this.partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "parts.json");

        return Files.readString(path);
    }

    @Override
    public String importParts() throws IOException {
        String json = this.readPartsFileContent();

        PartImportDTO[] importDTOs = this.gson.fromJson(json, PartImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(PartImportDTO dto) {
        Set<ConstraintViolation<PartImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid part";
        }

        Optional<Part> optPart = this.partsRepository.findByPartName(dto.getPartName());

        if (optPart.isPresent()) {
            return "Invalid part";
        }
        Part part = this.modelMapper.map(dto, Part.class);

        this.partsRepository.save(part);


        return "Successfully imported part " + part.getPartName() + " " + part.getPrice();
    }
}
