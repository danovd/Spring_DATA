package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.EmployeeCardImportDTO;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private static final String EMPLOYEE_CARDS_FILE_PATH = PATH_TO_FILES + "employee-cards.json";
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
        return fileUtil.readFile(EMPLOYEE_CARDS_FILE_PATH);
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {

        String json = this.readEmployeeCardsJsonFile();

        EmployeeCardImportDTO[] importDTOs = this.gson.fromJson(json, EmployeeCardImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(EmployeeCardImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return INCORRECT_DATA_MESSAGE;
        }

        Optional<EmployeeCard> optEC = this.employeeCardRepository.findByNumber(dto.getNumber());


        if (optEC.isPresent()) {
            return INCORRECT_DATA_MESSAGE;
        }

        EmployeeCard ec= this.modelMapper.map(dto, EmployeeCard.class);


        this.employeeCardRepository.save(ec);

        return String.format(SUCCESSFUL_IMPORT_MESSAGE, "Employee Card", ec.getNumber());

    }
}
