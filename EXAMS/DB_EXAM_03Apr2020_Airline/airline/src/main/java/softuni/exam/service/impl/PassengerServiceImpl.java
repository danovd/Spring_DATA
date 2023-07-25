package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final String PASSENGERS_FILE_PATH = "src/main/resources/files/json/passengers.json";
    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;

    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGERS_FILE_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        return null;
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return null;
    }
}
