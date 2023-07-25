package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.PlaneService;
@Service
public class PlaneServiceImpl implements PlaneService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPlanesFileContent() {
        return null;
    }

    @Override
    public String importPlanes() {
        return null;
    }
}
