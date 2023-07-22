package softuni.exam.service;


import java.io.IOException;

public interface AgentService {

    boolean areImported();

    String readAgentsFromFile() throws IOException;
	
	String importAgents() throws IOException;
}
