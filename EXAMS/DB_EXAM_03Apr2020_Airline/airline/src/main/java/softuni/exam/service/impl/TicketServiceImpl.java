package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportTicketDto;
import softuni.exam.models.dto.ImportTicketRootDTO;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Plane;
import softuni.exam.models.entity.Ticket;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private static final String TICKETS_FILE_PATH = "src/main/resources/files/xml/tickets.xml";
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;


    private final TownRepository townRepository;
    private final PlaneRepository planeRepository;
    private final PassengerRepository passengerRepository;



    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, TownRepository townRepository, PlaneRepository planeRepository, PassengerRepository passengerRepository) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.townRepository = townRepository;
        this.planeRepository = planeRepository;
        this.passengerRepository = passengerRepository;
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
    public String importTickets() throws JAXBException {
        ImportTicketRootDTO shopRootDTOs = this.xmlParser.fromFile(TICKETS_FILE_PATH, ImportTicketRootDTO.class);
        return shopRootDTOs.getTickets().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportTicketDto dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Ticket1";
        }

        Optional<Ticket> optTicket = this.ticketRepository.findBySerialNumber(dto.getSerialNumber());


        if (optTicket.isPresent()) {
            return "Invalid Ticket2";
        }


        Ticket ticket = this.modelMapper.map(dto, Ticket.class);

        //// SET to-town
        Optional<Town> toTown = townRepository.findByName(dto.getToTown().getName());
        if(toTown.isEmpty()){
            return "Invalid Ticket3";}
        ticket.setToTown(toTown.get());
        /////////////////////////
        ///SET from-town
        Optional<Town> fromTown = townRepository.findByName(dto.getFromTown().getName());
        if(fromTown.isEmpty()){return "Invalid Ticket4";}
        ticket.setFromTown(fromTown.get());
        /////

        ///SET passenger
        Optional<Passenger> passenger = passengerRepository.findByEmail(dto.getPassenger().getEmail());
        if(passenger.isEmpty()){return "Invalid Ticket5";}
        ticket.setPassenger(passenger.get());
        ////

        //// SET plane
        Optional<Plane> plane = planeRepository.findByRegisterNumber(dto.getPlane().getRegisterNumber());
        if(plane.isEmpty()){return "Invalid Ticket6";}
        ticket.setPlane(plane.get());
        /////

/*

    if(ticket.getToTown() == null || ticket.getFromTown() == null
            || ticket.getPassenger() == null || ticket.getPlane() == null
    || ticket.getPlane().getRegisterNumber() == null || ticket.getPassenger().getEmail() == null
    || ticket.getToTown().getName() == null || ticket.getFromTown().getName() == null){
        return "Invalid Ticket7";
    }
*/
        this.ticketRepository.save(ticket);

        return"Successfully imported Ticket " + ticket.getFromTown().getName() + " - " + ticket.getToTown().getName();
    }
}
