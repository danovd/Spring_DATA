package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }


    @Override
    public boolean areImported() {
        return this.partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "json", "parts.json");

        return String.join("\n", Files.readString(path));
    }

    @Override
    public String importParts() throws IOException {
        return null;
    }
}
