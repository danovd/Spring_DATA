package softuni.exam.service;


import java.io.IOException;


public interface MechanicsService {

    boolean areImported();

    String readMechanicsFromFile() throws IOException;

    String importMechanics() throws IOException;
}
