package hiberspring.service;

import java.io.IOException;

public interface EmployeeCardService {

    Boolean employeeCardsAreImported();

    String readEmployeeCardsJsonFile() throws IOException;

    String importEmployeeCards(String employeeCardsFileContent);
}
