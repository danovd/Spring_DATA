package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportForecastRootDTO;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String FORECASTS_FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;
    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository) throws JAXBException {

        this.forecastRepository = forecastRepository;


        JAXBContext context = JAXBContext.newInstance(ImportForecastRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

    }


    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECASTS_FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        return null;
    }

    @Override
    public String exportForecasts() {
        return null;
    }
}
