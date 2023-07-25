package softuni.exam.service;


import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PlaneService {

    boolean areImported();

    String readPlanesFileContent() throws IOException;
	
	String importPlanes() throws JAXBException;

}
