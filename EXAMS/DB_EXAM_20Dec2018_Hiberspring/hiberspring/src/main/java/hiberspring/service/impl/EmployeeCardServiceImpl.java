package hiberspring.service.impl;

import hiberspring.service.EmployeeCardService;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    @Override
    public Boolean employeeCardsAreImported() {
        return null;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return null;
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) {
        return null;
    }
}
