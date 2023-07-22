package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {


    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        return null;
    }
}
