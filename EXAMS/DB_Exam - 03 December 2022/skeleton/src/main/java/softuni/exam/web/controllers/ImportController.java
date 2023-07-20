package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.PartService;
import softuni.exam.service.TaskService;
import softuni.exam.service.MechanicService;
import softuni.exam.service.CarService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final PartService partService;
    private final TaskService taskService;
    private final MechanicService mechanicService;
    private final CarService carService;

    @Autowired
    public ImportController(PartService partService, TaskService taskService, MechanicService mechanicService, CarService carService) {
        this.partService = partService;
        this.taskService = taskService;
        this.mechanicService = mechanicService;
        this.carService = carService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.partService.areImported(),
                this.mechanicService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.carService.areImported(),
                this.taskService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/cars")
    public ModelAndView importCars() throws IOException {
        String carsXmlFileContent = this.carService.readCarsFromFile();
        return super.view("xml/import-cars", "cars", carsXmlFileContent);
    }

    @PostMapping("/cars")
    public ModelAndView importCarsConfirm() throws JAXBException, IOException {
        System.out.println(this.carService.importCars());

        return super.redirect("/import/xml");
    }

    @GetMapping("/tasks")
    public ModelAndView importTasks() throws IOException {
        String tasksXmlFileContent = this.taskService.readTasksFileContent();

        return super.view("xml/import-tasks", "tasks", tasksXmlFileContent);
    }

    @PostMapping("/tasks")
    public ModelAndView importTasksConfirm() throws JAXBException, FileNotFoundException, IOException {
        System.out.println(this.taskService.importTasks());

        return super.redirect("/import/xml");
    }

    @GetMapping("/parts")
    public ModelAndView importParts() throws IOException {
        String fileContent = this.partService.readPartsFileContent();

        return super.view("json/import-parts", "parts", fileContent);
    }

    @PostMapping("/parts")
    public ModelAndView importPartsConfirm() throws IOException {
        System.out.println(this.partService.importParts());
        return super.redirect("/import/json");
    }

    @GetMapping("/mechanics")
    public ModelAndView importMechanics() throws IOException {
        String fileContent = this.mechanicService.readMechanicsFromFile();

        return super.view("json/import-mechanics", "mechanics", fileContent);
    }

    @PostMapping("/mechanics")
    public ModelAndView importMechanicsConfirm() throws IOException, JAXBException {
        System.out.println(this.mechanicService.importMechanics());
        return super.redirect("/import/json");
    }
}
