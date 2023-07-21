package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    private static final String CITIES_FILE_PATH = "src/main/resources/files/json/cities.json";

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;
    @Autowired
    public CityServiceImpl(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;


        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();
    }


    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        // version 1:
        return Files.readString(Path.of(CITIES_FILE_PATH));

        // version 2:
        //   Path path = Path.of("src", "main", "resources", "files", "json", "cities.json");
        //   return Files.readString(path);
    }

    @Override
    public String importCities() throws IOException {
        String json = this.readCitiesFileContent();

        CityImportDTO[] importDTOs = this.gson.fromJson(json, CityImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(CityImportDTO dto) {
        Set<ConstraintViolation<CityImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid city";
        }

        Optional<City> optCity = this.cityRepository.findByCityName(dto.getCityName());

        if (optCity.isPresent()) {
            return "Invalid city";
        }

     //   Converter<Long, Country> converter = context -> context.getSource() == null ?
     //           null : new Country().setId(context.getSource());

     //   TypeMap<City, CityImportDTO> typeMap = this.modelMapper.createTypeMap(City.class, CityImportDTO.class);
     //   typeMap.addMappings(map -> map.using(converter).map(City::getCountry, CityImportDTO::setCountry));




     //   this.modelMapper.addConverter((Converter<Long, Country>)
     //           ctx -> {Optional<Country> countryById = countryRepository.getCountryById(ctx.getSource());

                    // If the country exists in the database, return it; otherwise, return null
     //               return countryById.orElse(null);});


        //   Converter<Long, Country> converter = context -> context.getSource() == null ?
         //          null : new Country().setId(context.getSource());

        //   TypeMap<City, CityImportDTO> typeMap = this.modelMapper.createTypeMap(City.class, CityImportDTO.class);
        //   typeMap.addMappings(map -> map.using(converter).map(City::getCountry, CityImportDTO::setCountry));






        City city = this.modelMapper.map(dto, City.class);



        ///////////////////////////  Operation SET Country of that City  ////////////////
        Optional<Country> countryById = countryRepository.getCountryById(dto.getCountry());
        city.setCountry(countryById.get());
        /////////////////////////////////////////////////////////////////////////////////


        System.out.println();
        System.out.println("CITY: " + city);
        this.cityRepository.save(city);

        return "Successfully imported country " + city.getCityName() + " - " + city.getPopulation();
    }
}
