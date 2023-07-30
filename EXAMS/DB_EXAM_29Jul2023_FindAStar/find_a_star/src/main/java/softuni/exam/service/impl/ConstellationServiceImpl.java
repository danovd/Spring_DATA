package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;


import java.io.IOException;


@Service
public class ConstellationServiceImpl implements ConstellationService {

    private static final String CONSTELLATIONS_FILE_PATH = "src/main/resources/files/json/constellations.json";
    private final ConstellationRepository constellationRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
  // private final ValidationUtil validator;
  //  private final FileUtil fileUtil;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson) {
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;

    }


    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return null;
    }

    @Override
    public String importConstellations() throws IOException {
      return null;
    }


}
