package softuni.exam.service;

import java.io.IOException;

public interface TeamService {

    String importTeams();

    boolean areImported();

    String readTeamsXmlFile() throws IOException;
}
