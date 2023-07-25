package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PassengerImportDTO;
import softuni.exam.models.entity.Passenger;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    private static final String PASSENGERS_FILE_PATH = "src/main/resources/files/json/passengers.json";
    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;

    private final TownRepository townRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, TownRepository townRepository) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.townRepository = townRepository;
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
        String json = this.readPassengersFileContent();

      PassengerImportDTO[] importDTOs = this.gson.fromJson(json, PassengerImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(PassengerImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Passenger";
        }

        Optional<Passenger> optPassenger = this.passengerRepository.findByEmail(dto.getEmail());


        if (optPassenger.isPresent()) {
            return "Invalid Passenger";
        }

        Passenger passenger= this.modelMapper.map(dto, Passenger.class);

        //// SET Town
        Optional<Town> town = townRepository.findByName(dto.getTown());
        if(town.isEmpty()){
            return "Invalid Passenger";
        }
        passenger.setTown(town.get());
        /////////////////////////

        this.passengerRepository.save(passenger);
        return "Successfully imported Passenger " + passenger.getLastName() + passenger.getEmail();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return null;
    }
}
