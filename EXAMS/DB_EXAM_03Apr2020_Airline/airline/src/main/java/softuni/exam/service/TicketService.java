package softuni.exam.service;


import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface TicketService {

    boolean areImported();

    String readTicketsFileContent() throws IOException;
	
	String importTickets() throws JAXBException;

}
