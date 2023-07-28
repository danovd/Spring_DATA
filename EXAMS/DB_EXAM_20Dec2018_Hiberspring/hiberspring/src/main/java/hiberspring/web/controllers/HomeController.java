package hiberspring.web.controllers;

import hiberspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final TownService townService;
    private final BranchService branchService;
    private final EmployeeCardService employeeCardService;
    private final ProductService productService;
    private final EmployeeService employeeService;

    @Autowired
    public HomeController(TownService townService, BranchService branchService, EmployeeCardService employeeCardService, ProductService productService, EmployeeService employeeService) {
        this.townService = townService;
        this.branchService = branchService;
        this.employeeCardService = employeeCardService;
        this.productService = productService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.townService.townsAreImported() &&
                this.branchService.branchesAreImported() &&
                this.employeeCardService.employeeCardsAreImported() &&
                this.productService.productsAreImported() &&
                this.employeeService.employeesAreImported();

        return super.view("index", "areImported", areImported);
    }
}
