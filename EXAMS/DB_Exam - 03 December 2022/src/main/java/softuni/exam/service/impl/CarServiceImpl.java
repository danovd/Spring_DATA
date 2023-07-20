package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class CarServiceImpl implements CarService {
    private static String CARS_FILE_PATH = "";
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "cars.xml");

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return String.join("\n", Files.readString(path));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        return null;
    }
}
