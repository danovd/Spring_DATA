package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.service.SellerService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class SellerServiceImpl implements SellerService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return null;
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        return null;
    }
}
