package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class TaskServiceImpl implements TaskService {
    private static String TASKS_FILE_PATH = "";
    private final TaskRepository taskRepository;
    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "tasks.xml");

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return String.join("\n", Files.readString(path));
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
