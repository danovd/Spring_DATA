package exam.web.controllers;

import exam.service.CustomerService;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.service.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final TownService townService;
    private final ShopService shopService;
    private final CustomerService customerService;
    private final LaptopService laptopService;

    public ImportController(TownService townService, ShopService shopService, CustomerService customerService, LaptopService laptopService) {
        this.townService = townService;
        this.shopService = shopService;
        this.customerService = customerService;
        this.laptopService = laptopService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.customerService.areImported(),
                this.laptopService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.townService.areImported(),
                this.shopService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/customers")
    public ModelAndView importCustomers() throws IOException {
        String fileContent = this.customerService.readCustomersFileContent();
        return super.view("json/import-customers", "customers", fileContent);
    }

    @PostMapping("/customers")
    public ModelAndView importCustomersConfirm() throws IOException {
        System.out.println(this.customerService.importCustomers());

        return super.redirect("/import/json");
    }

    @GetMapping("/laptops")
    public ModelAndView importLaptops() throws IOException {
        String fileContent = this.laptopService.readLaptopsFileContent();

        return super.view("json/import-laptops", "laptops", fileContent);
    }

    @PostMapping("/laptops")
    public ModelAndView importLaptopsConfirm() throws  FileNotFoundException, IOException {
        System.out.println(this.laptopService.importLaptops());

        return super.redirect("/import/json");
    }

    @GetMapping("/towns")
    public ModelAndView importTowns() throws IOException {
        String fileContent = this.townService.readTownsFileContent();

        return super.view("xml/import-towns", "towns", fileContent);
    }

    @PostMapping("/towns")
    public ModelAndView importTownsConfirm() throws IOException, JAXBException {
        System.out.println(this.townService.importTowns());
        return super.redirect("/import/xml");
    }

    @GetMapping("/shops")
    public ModelAndView importShops() throws IOException {
        String fileContent = this.shopService.readShopsFileContent();

        return super.view("xml/import-shops", "shops", fileContent);
    }

    @PostMapping("/shops")
    public ModelAndView importShopsConfirm() throws IOException, JAXBException {
        System.out.println(this.shopService.importShops());
        return super.redirect("/import/xml");
    }
}
