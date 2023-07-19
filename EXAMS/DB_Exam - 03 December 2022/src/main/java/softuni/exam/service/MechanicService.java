package softuni.exam.service;


import java.io.IOException;

// TODO: Implement all methods
public interface MechanicService {

    boolean areImported();

    String readMechanicsFromFile() throws IOException;

    String importMechanics() throws IOException;
}
