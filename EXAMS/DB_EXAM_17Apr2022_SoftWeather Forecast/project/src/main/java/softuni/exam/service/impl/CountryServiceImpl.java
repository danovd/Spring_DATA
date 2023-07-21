package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private static final String COUNTRIES_FILE_PATH = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

    }


    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        // version 1:
        return Files.readString(Path.of(COUNTRIES_FILE_PATH));


        // version 2:
     //   Path path = Path.of("src", "main", "resources", "files", "json", "countries.json");
     //   return Files.readString(path);
    }

    @Override
    public String importCountries() throws IOException {
        String json = this.readCountriesFromFile();

        CountryImportDTO[] importDTOs = this.gson.fromJson(json, CountryImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(CountryImportDTO dto) {
        Set<ConstraintViolation<CountryImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid country";
        }

        Optional<Country> optCountry = this.countryRepository.findByCountryName(dto.getCountryName());

        if (optCountry.isPresent()) {
            return "Invalid country";
        }

        Country country = this.modelMapper.map(dto, Country.class);

        this.countryRepository.save(country);

        return "Successfully imported country " + country.getCountryName() + " - " + country.getCurrency();
    }
}
