package hiberspring.service.impl;

import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class ProductServiceImpl implements ProductService {
    private static final String EMPLOYEES_FILE_PATH = "src/main/resources/files/products.xml";
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
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
