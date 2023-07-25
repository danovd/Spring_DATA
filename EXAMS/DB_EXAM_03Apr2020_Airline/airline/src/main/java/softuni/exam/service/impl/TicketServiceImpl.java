package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TicketServiceImpl implements TicketService {

    private static final String TICKETS_FILE_PATH = "src/main/resources/files/xml/tickets.xml";
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {

        return Files.readString(Path.of(TICKETS_FILE_PATH));
    }

    @Override
    public String importTickets() {
        return null;
    }
}
