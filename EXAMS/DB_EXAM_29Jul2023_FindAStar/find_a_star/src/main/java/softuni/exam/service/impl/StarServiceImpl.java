package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;

public class StarServiceImpl implements StarService {
    private static final String STARS_FILE_PATH = "src/main/resources/files/json/stars.json";
    private final StarRepository starRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    private final AstronomerRepository astronomerRepository;

    private final ConstellationRepository constellationRepository;
    
    public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil, AstronomerRepository astronomerRepository, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.astronomerRepository = astronomerRepository;
        this.constellationRepository = constellationRepository;



    }
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importStars() throws IOException {
        return null;
    }

    @Override
    public String exportStars() {
        return null;
    }
}
