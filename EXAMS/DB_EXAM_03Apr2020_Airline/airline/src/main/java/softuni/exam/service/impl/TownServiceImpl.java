package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.TownService;
@Service
public class TownServiceImpl implements TownService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readTownsFileContent() {
        return null;
    }

    @Override
    public String importTowns() {
        return null;
    }
}
