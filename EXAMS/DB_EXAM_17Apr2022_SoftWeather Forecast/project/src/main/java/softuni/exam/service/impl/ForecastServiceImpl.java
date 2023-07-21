package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportForecastDTO;
import softuni.exam.models.dto.ImportForecastRootDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static final String FORECASTS_FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final CityRepository cityRepository;
    private final ForecastRepository forecastRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;
    @Autowired
    public ForecastServiceImpl(CityRepository cityRepository, ForecastRepository forecastRepository) throws JAXBException {
        this.cityRepository = cityRepository;

        this.forecastRepository = forecastRepository;


        JAXBContext context = JAXBContext.newInstance(ImportForecastRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();


        modelMapper.addConverter(new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(MappingContext<String, LocalTime> context) {
                String dateString = context.getSource();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                return LocalTime.parse(dateString, formatter);
            }
        });

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

        Path path = Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");


        ImportForecastRootDTO forecastRootDTOs = (ImportForecastRootDTO) this.unmarshaller.unmarshal(

                new FileReader(path.toAbsolutePath().toString()));

        return forecastRootDTOs.getForecasts().stream().map(this::importTask).collect(Collectors.joining("\n"));
    }

    private String importTask(ImportForecastDTO dto) {
        Set<ConstraintViolation<ImportForecastDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid forecast";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");



  //      LocalTime sunset = LocalTime.parse(dto.getSunset(), formatter);
  //      LocalTime sunrise = LocalTime.parse(dto.getSunrise(), formatter);



        Optional<City> cityById = cityRepository.getCityById(dto.getCity());



        Optional<Forecast> optForecast = this.forecastRepository.findByDayOfWeekAndCity(dto.getDayOfWeek(), cityById);


        if (optForecast.isPresent()) {
            return "Invalid forecast";
        }

        Forecast forecast = this.modelMapper.map(dto, Forecast.class);



        ///////////////////////////  Operation SET City of that Forecast  ////////////////
        forecast.setCity(cityById.get());
        /////////////////////////////////////////////////////////////////////////////////



       this.forecastRepository.save(forecast);

        return"Successfully imported forecast " + forecast.getDayOfWeek() + " - " + forecast.getMaxTemperature();

    }

    @Override
    public String exportForecasts() {

        List<Forecast> forecasts = forecastRepository.findAllByCityPopulationLessThanAndDayOfWeekOrderByMaxTemperatureDescIdAsc(150000 , DayOfWeek.SUNDAY);

        return forecasts.stream()
                .map(Forecast::toString)
                .collect(Collectors.joining("\n"));
    }
}
