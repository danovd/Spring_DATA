package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.ForecastService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class ForecastServiceImpl implements ForecastService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return null;
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        return null;
    }

    @Override
    public String exportForecasts() {
        return null;
    }
}
