package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface TeamService {

    String importTeams() throws JAXBException;

    boolean areImported();

    String readTeamsXmlFile() throws IOException;
}
