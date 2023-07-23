package exam.service.impl;

import exam.model.dto.ImportShopDto;
import exam.model.dto.ImportShopRootDTO;
import exam.model.dto.ImportTownDto;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private static final String SHOPS_FILE_PATH = "src/main/resources/files/xml/shops.xml";
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final TownRepository townRepository;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOPS_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ImportShopRootDTO shopRootDTOs = this.xmlParser.fromFile(SHOPS_FILE_PATH, ImportShopRootDTO.class);
        return shopRootDTOs.getShops().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportShopDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid shop";
        }

        Optional<Shop> optShop = this.shopRepository.findByName(dto.getName());


        if (optShop.isPresent()) {
            return "Invalid shop";
        }


        Shop shop = this.modelMapper.map(dto, Shop.class);

        //// SET Town
        Town town = townRepository.getTownByName(dto.getTown().getName());
        shop.setTown(town);
        /////////////////////////



        this.shopRepository.save(shop);

        return"Successfully imported Shop " + shop.getName() + " - " + shop.getIncome();
    }
}
