package hiberspring.service.impl;

import hiberspring.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Boolean employeesAreImported() {
        return null;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return null;
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
