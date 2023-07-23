package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCarDTO;
import softuni.exam.models.dto.ImportCarRootDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



@Service
public class CarsServiceImpl implements CarsService {
    private static String CARS_FILE_PATH =
            Path.of("src", "main", "resources", "files", "xml", "parts.json").toAbsolutePath().toString();;


    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "cars.xml");
    private final CarsRepository carsRepository;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    public CarsServiceImpl(CarsRepository carsRepository) throws JAXBException {
        this.carsRepository = carsRepository;


        JAXBContext context = JAXBContext.newInstance(ImportCarRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        ImportCarRootDTO carRootDTOs = (ImportCarRootDTO) this.unmarshaller.unmarshal(
                new FileReader(path.toAbsolutePath().toString()));

        return carRootDTOs.getCars().stream().map(this::importCar).collect(Collectors.joining("\n"));
    }

    private String importCar(ImportCarDTO dto) {

        Set<ConstraintViolation<ImportCarDTO>> errors =
                this.validator.validate(dto);


        if (!errors.isEmpty()) {
            return "Invalid car";
        }

        Optional<Car> optCar = this.carsRepository.findByPlateNumber(dto.getPlateNumber());

        if (optCar.isPresent()) {
            return "Invalid car";
        }

        Car car = this.modelMapper.map(dto, Car.class);

        this.carsRepository.save(car);

        return"Successfully imported car " + car.getCarMake() + " - " + car.getCarModel();
    }
}
