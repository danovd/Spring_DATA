package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarImportDTO;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.models.entity.StarType.RED_GIANT;

@Service
public class StarServiceImpl implements StarService {

    private static final String STARS_FILE_PATH = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    private final AstronomerRepository astronomerRepository;

    private final ConstellationRepository constellationRepository;
    @Autowired
    public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil, AstronomerRepository astronomerRepository, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.astronomerRepository = astronomerRepository;
        this.constellationRepository = constellationRepository;



    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(STARS_FILE_PATH));
        // return fileUtil.readFile(STARS_FILE_PATH);
    }

    @Override
    public String importStars() throws IOException {
        String json = this.readStarsFileContent();

        StarImportDTO[] importDTOs = this.gson.fromJson(json, StarImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(StarImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if(!isValid){
            return "Invalid star";
        }

        Optional<Star> optStar = this.starRepository.findByName(dto.getName());


        if (optStar.isPresent()) {
            return "Invalid star";
        }

        Star star = this.modelMapper.map(dto, Star.class);

        // Set constellation:
        Optional<Constellation> constellation = this.constellationRepository.findById(dto.getConstellation());
        star.setConstellation(constellation.get());
        /////

        this.starRepository.save(star);

        return String.format("Successfully imported star %s - %.2f light years", star.getName(), star.getLightYears());

    }

    @Override
    public String exportStars() {

        Set<Long> allObservedStarsIds = this.astronomerRepository.findAll()
                .stream().map(astronomer -> astronomer.getObservingStar().getId())
                .collect(Collectors.toSet());


        List<Star> stars = this.starRepository.findAllByIdNotInAndStarTypeOrderByLightYearsAsc(allObservedStarsIds, RED_GIANT);

        return stars.stream()
                .map(Star::toString)
                .collect(Collectors.joining("\n"));
    }
}
