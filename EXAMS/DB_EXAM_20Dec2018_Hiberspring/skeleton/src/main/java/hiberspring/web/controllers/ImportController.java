package hiberspring.web.controllers;

import hiberspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final TownService townService;
    private final BranchService branchService;
    private final EmployeeCardService employeeCardService;
    private final ProductService productService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportController(TownService townService, BranchService branchService, EmployeeCardService employeeCardService, ProductService productService, EmployeeService employeeService) {
        this.townService = townService;
        this.branchService = branchService;
        this.employeeCardService = employeeCardService;
        this.productService = productService;
        this.employeeService = employeeService;
    }

    @GetMapping("/json")
    public ModelAndView importJson() {
        boolean[] areImported = new boolean[] {
            this.townService.townsAreImported(),
            this.branchService.branchesAreImported(),
            this.employeeCardService.employeeCardsAreImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }

    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[] {
                this.productService.productsAreImported(),
                this.employeeService.employeesAreImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }

    @GetMapping("/towns")
    public ModelAndView importTowns() throws IOException {
        return super.view("json/import-towns", "towns", this.townService.readTownsJsonFile());
    }

    @PostMapping("/towns")
    public ModelAndView importTownsConfirm(@RequestParam(name = "towns") String towns) {
        System.out.println(this.townService.importTowns(towns));

        return super.redirect("/import/json");
    }

    @GetMapping("/branches")
    public ModelAndView importBranches() throws IOException {
        return super.view("json/import-branches", "branches", this.branchService.readBranchesJsonFile());
    }

    @PostMapping("/branches")
    public ModelAndView importBranchesConfirm(@RequestParam(name = "branches") String branches) {
        System.out.println(this.branchService.importBranches(branches));

        return super.redirect("/import/json");
    }

    @GetMapping("/employee-cards")
    public ModelAndView importEmployeeCards() throws IOException {
        return super.view("json/import-employee-cards", "employeeCards", this.employeeCardService.readEmployeeCardsJsonFile());
    }

    @PostMapping("/employee-cards")
    public ModelAndView importEmployeeCardsConfirm(@RequestParam(name = "employeeCards") String employeeCards) {
        System.out.println(this.employeeCardService.importEmployeeCards(employeeCards));

        return super.redirect("/import/json");
    }

    @GetMapping("/products")
    public ModelAndView importProducts() throws IOException {
        return super.view("xml/import-products", "products", this.productService.readProductsXmlFile());
    }

    @PostMapping("/products")
    public ModelAndView importProductsConfirm() throws JAXBException {
        System.out.println(this.productService.importProducts());

        return super.redirect("/import/xml");
    }

    @GetMapping("/employees")
    public ModelAndView importEmployees() throws IOException {
        return super.view("xml/import-employees", "employees", this.employeeService.readEmployeesXmlFile());
    }

    @PostMapping("/employees")
    public ModelAndView importEmployeesConfirm() throws JAXBException {
        System.out.println(this.employeeService.importEmployees());

        return super.redirect("/import/xml");
    }
}
