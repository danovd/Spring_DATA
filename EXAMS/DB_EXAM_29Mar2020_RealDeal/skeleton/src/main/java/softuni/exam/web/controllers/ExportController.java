package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {


    private final CarService carService;


    @Autowired
    public ExportController(CarService carService) {
        this.carService = carService;
   
    }


    @GetMapping("/cars-by-pictures")
    public ModelAndView exportCarsByPictures(){
        String carsByPictures = this.carService
                .getCarsOrderByPicturesCountThenByMake();

        return super.view("export/export-cars-by-pictures.html","carsByPictures", carsByPictures);
    }
}
