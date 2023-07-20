package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;

    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }


    @Override
    public boolean areImported() {
        return this.mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "mechanics.json");

        return String.join("\n", Files.readString(path));
    }

    @Override
    public String importMechanics() throws IOException {
        return null;
    }
}
