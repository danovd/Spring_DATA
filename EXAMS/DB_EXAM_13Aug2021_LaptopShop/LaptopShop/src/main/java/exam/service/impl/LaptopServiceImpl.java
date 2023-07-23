package exam.service.impl;

import exam.service.LaptopService;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class LaptopServiceImpl implements LaptopService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importLaptops() throws IOException {
        return null;
    }

    @Override
    public String exportBestLaptops() {
        return null;
    }
}
