package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_PATH = "src/main/resources/files/users.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validator, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.userRepository = userRepository;
    }


    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        return null;
    }

    @Override
    public String exportUsersWithTheirPosts() {
        return null;
    }
}
