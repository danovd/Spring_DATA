package exam.service.impl;

import exam.service.ShopService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
@Service
public class ShopServiceImpl implements ShopService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return null;
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        return null;
    }
}
