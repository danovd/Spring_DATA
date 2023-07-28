package hiberspring.service.impl;

import hiberspring.domain.dtos.ImportEmployeeDto;
import hiberspring.domain.dtos.ImportEmployeeRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEES_FILE_PATH = PATH_TO_FILES + "employees.xml";
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil, EmployeeCardRepository employeeCardRepository, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return fileUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importEmployees() throws JAXBException {
        ImportEmployeeRootDTO shopRootDTOs = this.xmlParser.parseXml(ImportEmployeeRootDTO.class, EMPLOYEES_FILE_PATH);
        return shopRootDTOs.getEmployees().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportEmployeeDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return INCORRECT_DATA_MESSAGE;
        }

        Optional<Employee> optEmpl = this.employeeRepository.findByFirstNameAndLastNameAndPositionAndEmployeeCardNumberAndBranchName(
                dto.getFirstName(),dto.getLastName(), dto.getPosition(), dto.getCard(), dto.getBranch()
        );

        if (optEmpl.isPresent()) {
            return INCORRECT_DATA_MESSAGE;
        }

        Optional<Employee> optEmployeeByCardOnly = this.employeeRepository.findByEmployeeCardNumber(dto.getCard());

        if (optEmployeeByCardOnly.isPresent()) {
            return INCORRECT_DATA_MESSAGE;
        }

        Employee emp = this.modelMapper.map(dto, Employee.class);


        /// SET Employee-card
        Optional<EmployeeCard> emplCard = this.employeeCardRepository.findByNumber(dto.getCard());
        if(emplCard.isEmpty()){
            return INCORRECT_DATA_MESSAGE;
        }
        emp.setEmployeeCard(emplCard.get());
        ////

        //// SET Employee branch
        Optional<Branch> branch = this.branchRepository.findByName(dto.getBranch());
        if(branch.isEmpty()){
            return INCORRECT_DATA_MESSAGE;
        }
        emp.setBranch(branch.get());
        /////




        this.employeeRepository.save(emp);

        return String.format(SUCCESSFUL_IMPORT_MESSAGE, "Employee", emp.getFirstName() + " " + emp.getLastName());
    }

    @Override
    public String exportProductiveEmployees() {
        return null;
    }
}
