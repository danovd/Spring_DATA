package softuni.exam.service;


import java.io.IOException;

public interface TicketService {

    boolean areImported();

    String readTicketsFileContent() throws IOException;
	
	String importTickets();

}
