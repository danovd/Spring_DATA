package exam.web.controllers;

import exam.service.LaptopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {


    private final LaptopService laptopService;


    public ExportController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping("/best-laptops")
    public ModelAndView exportCarsByPictures() {
        String laptops = this.laptopService
                .exportBestLaptops();
        return super.view("export/export-best-laptops", "laptops", laptops);
    }
}
