package softuni.exam.service.impl;

import softuni.exam.service.TaskService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
// TODO: Implement all methods
public class TaskServiceImpl implements TaskService {
    private static String TASKS_FILE_PATH = "";

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return null;
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        return null;
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return null;
    }
}
