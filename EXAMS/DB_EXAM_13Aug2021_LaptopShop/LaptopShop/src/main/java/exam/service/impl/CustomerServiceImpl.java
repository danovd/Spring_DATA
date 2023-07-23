package exam.service.impl;

import exam.service.CustomerService;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return null;
    }

    @Override
    public String importCustomers() throws IOException {
        return null;
    }
}
