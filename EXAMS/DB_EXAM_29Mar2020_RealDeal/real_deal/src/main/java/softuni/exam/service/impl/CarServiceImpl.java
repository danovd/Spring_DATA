package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.CarService;

import java.io.IOException;
@Service
public class CarServiceImpl implements CarService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importCars() throws IOException {
        return null;
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
