package softuni.exam.service;

import java.io.IOException;

// TODO: Implement all methods
public interface PartService {

    boolean areImported();

    String readPartsFileContent() throws IOException;

    String importParts() throws IOException;
}
