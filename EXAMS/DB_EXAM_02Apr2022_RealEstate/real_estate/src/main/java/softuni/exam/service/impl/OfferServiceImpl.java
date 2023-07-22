package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportOfferDTO;
import softuni.exam.models.dto.ImportOfferRootDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";


    private final OfferRepository offerRepository;
    private final ApartmentRepository apartmentRepository;
    private final AgentRepository agentRepository;


    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;


    public OfferServiceImpl(OfferRepository offerRepository, ApartmentRepository apartmentRepository, AgentRepository agentRepository) throws JAXBException {
        this.offerRepository = offerRepository;
        this.apartmentRepository = apartmentRepository;
        this.agentRepository = agentRepository;

        JAXBContext context = JAXBContext.newInstance(ImportOfferRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();


        modelMapper.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> context) {
                String dateString = context.getSource();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(dateString, formatter);
            }
        });

        /*
         modelMapper.addConverter((Converter<String, LocalDate>) context1 -> {
            String dateString = context1.getSource();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, formatter);
        });
         */



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
        Path path = Path.of("src", "main", "resources", "files", "xml", "offers.xml");


        ImportOfferRootDTO forecastRootDTOs = (ImportOfferRootDTO) this.unmarshaller.unmarshal(

                new FileReader(path.toAbsolutePath().toString()));

        return forecastRootDTOs.getOffers().stream().map(this::importOffer).collect(Collectors.joining("\n"));
    }

    private String importOffer(ImportOfferDTO dto) {
        Set<ConstraintViolation<ImportOfferDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid offer";
        }



        Optional<Offer> optOffer = this.offerRepository.findByPublishedOn(dto.getPublishedOn());


        if (optOffer.isPresent()) {
            return "Invalid offer";
        }


        Optional<Agent> agent = this.agentRepository.findByFirstName(dto.getAgent().getName());
        Optional<Apartment> apartment = this.apartmentRepository.findById(dto.getApartment().getId());



        Offer offer = this.modelMapper.map(dto, Offer.class);

        if(agent.isEmpty() || apartment.isEmpty()){
            return "Invalid offer";
        }
        agent.ifPresent(offer::setAgent);
        apartment.ifPresent(offer::setApartment);


        this.offerRepository.save(offer);

        return"Successfully imported offer " + offer.getPrice();
    }

    @Override
    public String exportOffers() {
        return null;
    }
}
