package hiberspring.service.impl;

import hiberspring.service.ProductService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Boolean productsAreImported() {
        return null;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return null;
    }

    @Override
    public String importProducts() throws JAXBException {
        return null;
    }
}
