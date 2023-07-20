package softuni.exam.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
import softuni.exam.service.ForecastService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class Init implements CommandLineRunner {
    private final CountryService countryService;
    private final CityService cityService;
    private final ForecastService forecastService;

    public Init(CountryService countryService, CityService cityService, ForecastService forecastService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.forecastService = forecastService;
    }

    @Override
    public void run(String... args) throws Exception {
//        initializeCountries();
//        initializeCities();
//        initializeForecasta();
//        exportForecasts();
    }

    private void exportForecasts() {
        forecastService.exportForecasts();
    }

    private void initializeForecasta() throws IOException, JAXBException {
        forecastService.importForecasts();
    }

    private void initializeCities() throws IOException {
        cityService.importCities();

    }

    private void initializeCountries() throws IOException {
        countryService.importCountries();
    }
}
