package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.CustomerImportDTO;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/files/json/customers.json";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final TownRepository townRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, TownRepository townRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        String json = this.readCustomersFileContent();

        CustomerImportDTO[] importDTOs = this.gson.fromJson(json, CustomerImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(CustomerImportDTO dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Customer";
        }

        Optional<Customer> optCustomer = this.customerRepository.findByEmail(dto.getEmail());


        if (optCustomer.isPresent()) {
            return "Invalid Customer";
        }

        Customer customer= this.modelMapper.map(dto, Customer.class);

        //// SET Town
        Town town = townRepository.getTownByName(dto.getTown().getName());
        customer.setTown(town);
        /////////////////////////

        this.customerRepository.save(customer);

        return "Successfully imported Customer " + customer.getFirstName() + " " + customer.getLastName() + " - " + customer.getEmail();
    }
}
