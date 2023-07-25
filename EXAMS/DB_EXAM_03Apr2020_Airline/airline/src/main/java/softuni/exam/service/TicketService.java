package softuni.exam.service;


//ToDo - Before start App implement this Service and set areImported to return false
public interface TicketService {

    boolean areImported();

    String readTicketsFileContent();
	
	String importTickets();

}
