package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.*;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: Implement all methods
@Service
public class TaskServiceImpl implements TaskService {
    private static String TASKS_FILE_PATH = "";
    private final TaskRepository taskRepository;
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final MechanicRepository mechanicRepository;


    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "tasks.xml");

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;


    public TaskServiceImpl(TaskRepository taskRepository, CarRepository carRepository, PartRepository partRepository,
                           MechanicRepository mechanicRepository) throws JAXBException {
        this.taskRepository = taskRepository;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mechanicRepository = mechanicRepository;

        JAXBContext context = JAXBContext.newInstance(ImportTaskRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();


        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> context) {
                String dateString = context.getSource();
                // Assuming the date format is "yyyy-MM-dd HH:mm:ss"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return LocalDateTime.parse(dateString, formatter);
            }
        });
/*
*/
    }

    @Override
    public boolean areImported() {
        return this.taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        ImportTaskRootDTO taskRootDTOs = (ImportTaskRootDTO) this.unmarshaller.unmarshal(
                new FileReader(path.toAbsolutePath().toString()));

        return taskRootDTOs.getTasks().stream().map(this::importTask).collect(Collectors.joining("\n"));
    }

    private String importTask(ImportTaskDTO dto) {




        Set<ConstraintViolation<ImportTaskDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid task";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the string into a LocalDateTime object using the formatter
        LocalDateTime dateTime = LocalDateTime.parse(dto.getDate(), formatter);

        Optional<Task> optTask = this.taskRepository.findByDate(dateTime);


        if (optTask.isPresent()) {
            return "Invalid task";
        }



        Optional<Car> car = this.carRepository.findById(dto.getCar().getId());
        Optional<Part> part = this.partRepository.findById(dto.getPart().getId());
        Optional<Mechanic> mechanic = this.mechanicRepository.findByFirstName(dto.getMechanic().getFirstName());


        Task task = this.modelMapper.map(dto, Task.class);

if(!mechanic.isPresent()){
    return "Invalid task";
}
        car.ifPresent(task::setCar);
        part.ifPresent(task::setPart);
        mechanic.ifPresent(task::setMechanic);



        this.taskRepository.save(task);

        return"Successfully imported task " + task.getPrice();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {

        List<Task> tasks = taskRepository.findByCarCarTypeOrderByPriceDesc(CarType.coupe);

        return tasks.stream()
                .map(Task::toString) // Use existing toString method
                .collect(Collectors.joining("\n")); // Concatenate the strings with a single newline in between
    }
}
