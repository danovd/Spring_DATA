package softuni.exam.service;

import java.io.IOException;

public interface PictureService {
    String importPictures();

    boolean areImported();

    String readPicturesXmlFile() throws IOException;
}
