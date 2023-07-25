package softuni.exam.service;


public interface TicketService {

    boolean areImported();

    String readTicketsFileContent();
	
	String importTickets();

}
