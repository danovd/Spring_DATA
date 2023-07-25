package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.PassengerService;

import java.io.IOException;
@Service
public class PassengerServiceImpl implements PassengerService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return null;
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
