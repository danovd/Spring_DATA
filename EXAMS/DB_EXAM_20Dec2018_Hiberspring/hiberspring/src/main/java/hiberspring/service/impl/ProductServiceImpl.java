package hiberspring.service.impl;

import hiberspring.domain.dtos.ImportProductDto;
import hiberspring.domain.dtos.ImportProductRootDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String EMPLOYEES_FILE_PATH = PATH_TO_FILES + "products.xml";;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    private final BranchRepository branchRepository;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return fileUtil.readFile(EMPLOYEES_FILE_PATH);
    }

    @Override
    public String importProducts() throws JAXBException {
        ImportProductRootDTO shopRootDTOs = this.xmlParser.parseXml(ImportProductRootDTO.class, EMPLOYEES_FILE_PATH);
        return shopRootDTOs.getProducts().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportProductDto dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return INCORRECT_DATA_MESSAGE;
        }

        Optional<Product> optProduct = this.productRepository.findByNameAndClientsAndBranchName(
                dto.getName(), dto.getClients(), dto.getBranch()
        );

        if (optProduct.isPresent()) {
            return INCORRECT_DATA_MESSAGE;
        }

        Product product = this.modelMapper.map(dto, Product.class);


        //// SET branch
        Optional<Branch> branch = this.branchRepository.findByName(dto.getBranch());
        if(branch.isEmpty()){
            return INCORRECT_DATA_MESSAGE;
        }
        product.setBranch(branch.get());
        /////




        this.productRepository.save(product);

        return String.format(SUCCESSFUL_IMPORT_MESSAGE, "Product", product.getName());

    }
}
