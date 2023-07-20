package softuni.exam.service.impl;

import softuni.exam.service.MechanicService;

import java.io.IOException;
// TODO: Implement all methods
public class MechanicServiceImpl implements MechanicService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return null;
    }

    @Override
    public String importMechanics() throws IOException {
        return null;
    }
}
