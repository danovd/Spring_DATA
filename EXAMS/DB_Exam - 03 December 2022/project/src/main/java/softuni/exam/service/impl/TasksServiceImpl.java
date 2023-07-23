package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.*;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;

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


@Service
public class TasksServiceImpl implements TasksService {
    private static String TASKS_FILE_PATH = "";
    private final TasksRepository tasksRepository;
    private final CarsRepository carsRepository;
    private final PartsRepository partsRepository;
    private final MechanicsRepository mechanicsRepository;


    private final Path path =
            Path.of("src", "main", "resources", "files", "xml", "tasks.xml");

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;


    public TasksServiceImpl(TasksRepository tasksRepository, CarsRepository carsRepository, PartsRepository partsRepository,
                            MechanicsRepository mechanicsRepository) throws JAXBException {
        this.tasksRepository = tasksRepository;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
        this.mechanicsRepository = mechanicsRepository;

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
        return this.tasksRepository.count() > 0;
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

        Optional<Task> optTask = this.tasksRepository.findByDate(dateTime);


        if (optTask.isPresent()) {
            return "Invalid task";
        }



        Optional<Car> car = this.carsRepository.findById(dto.getCar().getId());
        Optional<Part> part = this.partsRepository.findById(dto.getPart().getId());
        Optional<Mechanic> mechanic = this.mechanicsRepository.findByFirstName(dto.getMechanic().getFirstName());


        Task task = this.modelMapper.map(dto, Task.class);

if(!mechanic.isPresent()){
    return "Invalid task";
}
        car.ifPresent(task::setCar);
        part.ifPresent(task::setPart);
        mechanic.ifPresent(task::setMechanic);



        this.tasksRepository.save(task);

        return"Successfully imported task " + task.getPrice();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {

        List<Task> tasks = tasksRepository.findByCarCarTypeOrderByPriceDesc(CarType.coupe);

        return tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
