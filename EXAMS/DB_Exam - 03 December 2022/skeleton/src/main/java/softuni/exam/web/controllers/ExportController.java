package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.TaskService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final TaskService taskService;

    @Autowired
    public ExportController(TaskService taskService) {

        this.taskService = taskService;
    }


    @GetMapping("/coupe-tasks")
    public ModelAndView exportTasksByTypeOrderByPrice() {
        String tasksByTypeOrderByPrice = this.taskService.getCoupeCarTasksOrderByPrice();

        return super.view("export/export-tasks-by-type-order-by-price.html", "tasksByTypeOrderByPrice", tasksByTypeOrderByPrice);
    }
}
