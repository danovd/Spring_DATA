package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.TicketService;
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readTicketsFileContent() {
        return null;
    }

    @Override
    public String importTickets() {
        return null;
    }
}
