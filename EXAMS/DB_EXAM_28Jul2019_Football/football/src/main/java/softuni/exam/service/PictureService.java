package softuni.exam.service;

//import jakarta.xml.bind.JAXBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PictureService {
    String importPictures() throws JAXBException, JAXBException;

    boolean areImported();

    String readPicturesXmlFile() throws IOException;
}
