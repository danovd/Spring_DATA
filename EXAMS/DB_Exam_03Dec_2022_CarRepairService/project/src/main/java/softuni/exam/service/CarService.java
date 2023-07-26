package softuni.exam.service;


import javax.xml.bind.JAXBException;
import java.io.IOException;


public interface CarService {

    boolean areImported();

    String readCarsFromFile() throws IOException;

    String importCars() throws IOException, JAXBException;
}
