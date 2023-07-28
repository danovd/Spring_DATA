package hiberspring.service.impl;

import hiberspring.service.TownService;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class TownServiceImpl implements TownService {
    @Override
    public Boolean townsAreImported() {
        return null;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return null;
    }

    @Override
    public String importTowns(String townsFileContent) {
        return null;
    }
}
