package softuni.exam.service;

import java.io.IOException;


public interface PartService {

    boolean areImported();

    String readPartsFileContent() throws IOException;

    String importParts() throws IOException;
}
