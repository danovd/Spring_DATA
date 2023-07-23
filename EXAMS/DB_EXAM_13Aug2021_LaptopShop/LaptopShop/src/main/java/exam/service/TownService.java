package exam.service;



import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;


public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws JAXBException, FileNotFoundException;
}
