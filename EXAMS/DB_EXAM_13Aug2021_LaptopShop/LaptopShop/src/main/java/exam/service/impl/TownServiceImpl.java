package exam.service.impl;

import exam.service.TownService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
@Service
public class TownServiceImpl implements TownService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importTowns() throws JAXBException, FileNotFoundException {
        return null;
    }
}
