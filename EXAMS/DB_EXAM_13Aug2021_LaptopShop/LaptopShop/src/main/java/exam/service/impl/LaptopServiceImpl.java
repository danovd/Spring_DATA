package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.LaptopImportDTO;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final String LAPTOPS_FILE_PATH = "src/main/resources/files/json/laptops.json";
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final ShopRepository shopRepository;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.shopRepository = shopRepository;
    }


    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOPS_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        String json = this.readLaptopsFileContent();

        LaptopImportDTO[] importDTOs = this.gson.fromJson(json, LaptopImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(LaptopImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Laptop";
        }

        Optional<Laptop> optLaptop = this.laptopRepository.findByMacAddress(dto.getMacAddress());


        if (optLaptop.isPresent()) {
            return "Invalid Laptop";
        }

        Laptop laptop= this.modelMapper.map(dto, Laptop.class);

        //// SET Shop
        Shop shop = shopRepository.getShopByName(dto.getShop().getName());
        laptop.setShop(shop);
        /////////////////////////

        this.laptopRepository.save(laptop);
        return "Successfully imported Laptop " + laptop.getMacAddress() + " - " + laptop.getCpuSpeed() + " - " +
                laptop.getRam() + " - " + laptop.getStorage();
    }

    @Override
    public String exportBestLaptops() {


        List<Laptop> laptops = laptopRepository.findAllByOrderByCpuSpeedDescRamDescMacAddressDesc();

        return laptops.stream()
                .map(Laptop::toString)
                .collect(Collectors.joining("\n"));
    }
}
