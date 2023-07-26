package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PictureImportDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURES_FILE_PATH = "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;

    private final CarRepository carRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readPicturesFromFile();

        PictureImportDTO[] importDTOs = this.gson.fromJson(json, PictureImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(PictureImportDTO dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid picture";
        }

        Optional<Picture> optPicture = this.pictureRepository.findByName(dto.getName());


        if (optPicture.isPresent()) {
            return "Invalid picture";
        }

        Picture picture= this.modelMapper.map(dto, Picture.class);

        //// SET CAR
        Optional<Car> car = carRepository.findById(dto.getCar());

        if(car.isEmpty()){
            return "Invalid picture";
        }
        picture.setCar(car.get());
        /////////////////////////

        this.pictureRepository.save(picture);
        return "Successfully imported picture " + picture.getName();

    }
}
