package softuni.exam.service;

import java.io.IOException;


public interface PartsService {

    boolean areImported();

    String readPartsFileContent() throws IOException;

    String importParts() throws IOException;
}
