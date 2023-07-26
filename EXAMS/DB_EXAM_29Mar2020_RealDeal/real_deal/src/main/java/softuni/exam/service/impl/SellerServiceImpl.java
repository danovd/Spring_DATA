package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportSellerDto;
import softuni.exam.models.dto.ImportSellerRootDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    private static final String SELLERS_FILE_PATH = "src/main/resources/files/xml/sellers.xml";
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        ImportSellerRootDTO planeRootDTOs = this.xmlParser.fromFile(SELLERS_FILE_PATH, ImportSellerRootDTO.class);
        return planeRootDTOs.getSellers().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportSellerDto dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid seller";
        }

        Optional<Seller> optSeller = this.sellerRepository.findByEmail(dto.getEmail());


        if (optSeller.isPresent()) {
            return "Invalid seller";
        }


        Seller seller = this.modelMapper.map(dto, Seller.class);


        this.sellerRepository.save(seller);

        return"Successfully imported Plane " + seller.getLastName() + " - " + seller.getEmail();

    }
}
