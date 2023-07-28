package hiberspring.service.impl;

import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEES_FILE_PATH = PATH_TO_FILES + "employee-cards.json";;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
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
        return null;
    }

    @Override
    public String exportProductiveEmployees() {
        return null;
    }
}
