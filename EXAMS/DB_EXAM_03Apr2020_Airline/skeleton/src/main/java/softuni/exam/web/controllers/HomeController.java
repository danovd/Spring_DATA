package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.*;

@Controller
public class HomeController extends BaseController {

    private final PassengerService passengerService;
    private final PlaneService planeService;
    private final TicketService ticketService;
    private final TownService townService;

    @Autowired
    public HomeController(PassengerService passengerService, PlaneService planeService, TicketService ticketService, TownService townService) {
        this.passengerService = passengerService;
        this.planeService = planeService;
        this.ticketService = ticketService;
        this.townService = townService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.passengerService.areImported() &&
                this.planeService.areImported() &&
                this.ticketService.areImported() &&
                this.townService.areImported();

        return super.view("index", "areImported", areImported);
    }
}
