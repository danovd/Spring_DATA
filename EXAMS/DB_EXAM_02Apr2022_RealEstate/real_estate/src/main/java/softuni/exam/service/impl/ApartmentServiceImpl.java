package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportApartmentDTO;
import softuni.exam.models.dto.ImportApartmentRootDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;

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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {


    private static final String APARTMENTS_FILE_PATH = "src/main/resources/files/xml/apartments.xml";

    private final ApartmentRepository apartmentRepository;

    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;

        JAXBContext context = JAXBContext.newInstance(ImportApartmentRootDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

    }


    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENTS_FILE_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        Path path = Path.of("src", "main", "resources", "files", "xml", "apartments.xml");


        ImportApartmentRootDTO apartmentRootDTOs = (ImportApartmentRootDTO) this.unmarshaller.unmarshal(

                new FileReader(path.toAbsolutePath().toString()));

        return apartmentRootDTOs.getApartments().stream().map(this::importApartment).collect(Collectors.joining("\n"));
    }

    private String importApartment(ImportApartmentDTO dto) {
        Set<ConstraintViolation<ImportApartmentDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid apartment";
        }


        Optional<Town> townById = townRepository.getTownByTownName(dto.getTown());



        // In my opinion, this is the proper way to find the exact apartment. According to conditions is other.
       // Optional<Apartment> optApartment = this.apartmentRepository.findByApartmentTypeAndAreaAndTown(dto.getApartmentType(), dto.getArea(), townById.get());

     //   <apartment>                                                                            <apartment>
     //       <apartmentType>two_rooms</apartmentType>              !=                                   <apartmentType>three_rooms</apartmentType>
     //       <area>53.47</area>                                                                         <area>53.47</area>
     //       <town>Lille</town>                                                                         <town>Lille</town>
     //   </apartment>                                                                               </apartment>


        Optional<Apartment> optApartment = this.apartmentRepository.findByAreaAndTown(dto.getArea(), townById.get());


        if (optApartment.isPresent()) {
            return "Invalid apartment";
        }

        Apartment apartment = this.modelMapper.map(dto, Apartment.class);



        ///////////////////////////  Operation SET City of that Forecast  ////////////////
        apartment.setTown(townById.get());
        /////////////////////////////////////////////////////////////////////////////////



        this.apartmentRepository.save(apartment);

        return"Successfully imported apartment " + apartment.getApartmentType() + " - " + apartment.getArea();
    }
}
