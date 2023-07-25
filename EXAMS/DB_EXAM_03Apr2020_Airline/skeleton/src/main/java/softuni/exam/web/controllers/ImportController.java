package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final PassengerService passengerService;
    private final PlaneService planeService;
    private final TicketService ticketService;
    private final TownService townService;

    @Autowired
    public ImportController(PassengerService passengerService, PlaneService planeService, TicketService ticketService, TownService townService) {
        this.passengerService = passengerService;
        this.planeService = planeService;
        this.ticketService = ticketService;
        this.townService = townService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.passengerService.areImported(),
                this.townService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.planeService.areImported(),
                this.ticketService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/planes")
    public ModelAndView importPlanes() throws IOException {
        String fileContent = this.planeService.readPlanesFileContent();
        return super.view("xml/import-planes", "planes", fileContent);
    }

    @PostMapping("/planes")
    public ModelAndView importPlanesConfirm() throws JAXBException, IOException {
        System.out.println(this.planeService.importPlanes());

        return super.redirect("/import/xml");
    }

    @GetMapping("/tickets")
    public ModelAndView importTickets() throws IOException {
        String fileContent = this.ticketService.readTicketsFileContent();

        return super.view("xml/import-tickets", "tickets", fileContent);
    }

    @PostMapping("/tickets")
    public ModelAndView importTicketsConfirm() throws JAXBException, FileNotFoundException, IOException {
        System.out.println(this.ticketService.importTickets());

        return super.redirect("/import/xml");
    }

    @GetMapping("/towns")
    public ModelAndView importTowns() throws IOException {
        String fileContent = this.townService.readTownsFileContent();

        return super.view("json/import-towns", "towns", fileContent);
    }

    @PostMapping("/towns")
    public ModelAndView importTownsConfirm() throws IOException {
        System.out.println(this.townService.importTowns());
        return super.redirect("/import/json");
    }

    @GetMapping("/passengers")
    public ModelAndView importPassengers() throws IOException {
        String fileContent = this.passengerService.readPassengersFileContent();

        return super.view("json/import-passengers", "passengers", fileContent);
    }

    @PostMapping("/passengers")
    public ModelAndView importPassengersConfirm() throws IOException, JAXBException {
        System.out.println(this.passengerService.importPassengers());
        return super.redirect("/import/json");
    }
}
