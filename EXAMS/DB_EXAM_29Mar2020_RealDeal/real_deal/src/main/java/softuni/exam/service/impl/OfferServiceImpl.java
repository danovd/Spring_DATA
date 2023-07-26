package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportOfferDto;
import softuni.exam.models.dto.ImportOfferRootDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        ImportOfferRootDTO planeRootDTOs = this.xmlParser.fromFile(OFFERS_FILE_PATH, ImportOfferRootDTO.class);
        return planeRootDTOs.getOffers().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportOfferDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid offer";
        }
        LocalDateTime dateTime;
      //  try {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    dateTime = LocalDateTime.parse(dto.getAddedOn(), formatter);
//}catch(Exception e){
  //  return "Invalid offer";
//}
        Optional<Offer> optOffer = this.offerRepository.findByDescriptionAndAddedOn(dto.getDescription(),dateTime);


        if (optOffer.isPresent()) {
            return "Invalid offer";
        }


        Offer offer = this.modelMapper.map(dto, Offer.class);

        // SET CAR
        Optional<Car> car = this.carRepository.findById(dto.getCar().getId());
        if(car.isEmpty()){
            return "Invalid offer";
        }
        offer.setCar(car.get());
        ///

        /// SET SELLER
        Optional<Seller> seller = this.sellerRepository.findById(dto.getSeller().getId());
        if(seller.isEmpty()){
            return "Invalid offer";
        }
        offer.setSeller(seller.get());
        ///

        this.offerRepository.save(offer);

        return"Successfully imported offer " + offer.getAddedOn() + " - " + offer.isHasGoldStatus();
    }
}
