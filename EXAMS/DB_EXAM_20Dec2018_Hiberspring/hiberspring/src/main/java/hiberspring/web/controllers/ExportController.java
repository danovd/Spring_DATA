package hiberspring.web.controllers;

import hiberspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final EmployeeService employeeService;

    @Autowired
    public ExportController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/productive-employees")
    public ModelAndView exportProductiveEmployees() {
        String exportResult = this.employeeService.exportProductiveEmployees();

        return super.view("export/export-productive-employees", "productiveEmployees", exportResult);
    }
}
