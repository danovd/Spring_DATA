package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountrySeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private static final String COUNTRIES_FILE_PATH = "src/main/resources/files/json/countries.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;

    public CountryServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files
                .readString(Path.of(COUNTRIES_FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {

        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson
                .fromJson(readCountriesFromFile(), CountrySeedDto[].class))
                .filter(countrySeedDto -> {
                    boolean isValid = validationUtil.isValid(countrySeedDto);

                    Optional<Country> countryByCountryName = countryRepository.findCountryByCountryName(countrySeedDto.getCountryName());
                    if (countryByCountryName.isPresent()){
                        isValid = false;
                    }

                    sb.append(isValid
                            ? String.format("Successfully imported country %s - %s", countrySeedDto.getCountryName()
                            , countrySeedDto.getCurrency())
                            : "Invalid country")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(countrySeedDto -> modelMapper.map(countrySeedDto, Country.class))
                .forEach(countryRepository::save);

        return sb.toString();
    }

    @Override
    public Country findCountryByName(String countryName) {

        return countryRepository.findCountryByCountryName(countryName).orElse(null);
    }

    @Override
    public Country saveNonExistentCountry(String countryByName) {
        Country country = countryRepository.save(new Country().setCountryName(countryByName));
        return country;
    }

    @Override
    public Optional<Country> getCountryById(Long countryId) {
        return countryRepository.findById(countryId);
    }

}
