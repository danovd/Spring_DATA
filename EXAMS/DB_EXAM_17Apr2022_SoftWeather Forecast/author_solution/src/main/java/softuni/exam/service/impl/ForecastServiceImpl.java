package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastSeedRootDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DaysOfWeek;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String FORECASTS_FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CityService cityService;

    public ForecastServiceImpl(ForecastRepository forecastRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files
                .readString(Path.of(FORECASTS_FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(FORECASTS_FILE_PATH, ForecastSeedRootDto.class)
                .getForecastSeedDtos()
                .stream()
                .filter(forecastSeedDto -> {
                    boolean isValid = validationUtil.isValid(forecastSeedDto);

                    City city = cityService.findCityById(forecastSeedDto.getCity());

                    if (city == null) {
                        isValid = false;
                    }

                    Forecast forecast = forecastRepository.findAllByCityAndDayOfWeek(city, forecastSeedDto.getDayOfWeek()).orElse(null);
                    if (forecast != null) {
                        isValid = false;
                    }

                    sb
                            .append(isValid
                                    ? String.format("Successfully import forecast %s - %.2f",
                                    forecastSeedDto.getDayOfWeek().toString(), forecastSeedDto.getMaxTemperature())
                                    : "Invalid forecast")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(forecastSeedDto -> {
                    Forecast forecast = modelMapper.map(forecastSeedDto, Forecast.class);

                    City city = cityService.findCityById(forecastSeedDto.getCity());
//
                    forecast.setCity(city);
//                    cityService.addAndSaveAddedForecast(forecast,city);

                    //                    Town townByName = townService.findTownByName(forecastSeedDto.getTown());
//                    apartment.setTown(townByName);

                    return forecast;
                })
                .forEach(forecastRepository::save);

        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();

        Set<Forecast> allByDayOfWeek_sunday = forecastRepository.findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DaysOfWeek.SUNDAY, 150000);

        allByDayOfWeek_sunday
                .forEach(f -> {
                    sb.append(String.format("City: %s\n" +
                                    "-min temperature: %.2f\n" +
                                    "--max temperature: %.2f\n" +
                                    "---sunrise: %s\n" +
                                    "-----sunset: %s",
                            f.getCity().getCityName(),
                            f.getMinTemperature(),
                            f.getMaxTemperature(),
                            f.getSunrise(),
                            f.getSunset()))
                            .append(System.lineSeparator());
                });

        return sb.toString().trim();
    }
}
