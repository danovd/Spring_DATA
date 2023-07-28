package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private static final String EMPLOYEE_CARDS_FILE_PATH = "src/main/resources/files/employee-cards.json";
    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
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
