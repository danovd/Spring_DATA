package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.ApartmentService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return null;
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        return null;
    }
}
