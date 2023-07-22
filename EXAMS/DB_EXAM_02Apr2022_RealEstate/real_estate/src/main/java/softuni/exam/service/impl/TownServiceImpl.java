package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.TownService;

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
    public String importTowns() throws IOException {
        return null;
    }
}
