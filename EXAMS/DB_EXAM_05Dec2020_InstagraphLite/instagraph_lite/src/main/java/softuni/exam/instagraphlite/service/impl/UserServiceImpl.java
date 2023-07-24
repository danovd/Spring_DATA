package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserImportDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_PATH = "src/main/resources/files/users.json";

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final UserRepository userRepository;

    private final PictureRepository pictureRepository;
    public UserServiceImpl(ModelMapper modelMapper, Gson gson, ValidationUtil validator, UserRepository userRepository, PictureRepository pictureRepository) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
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
        String json = this.readFromFileContent();

        UserImportDTO[] importDTOs = this.gson.fromJson(json, UserImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(UserImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid User";
        }

        Optional<User> optUser = this.userRepository.findByUsername(dto.getUsername());


        if (optUser.isPresent()) {
            return "Invalid User";
        }

    // This is another method available to set from string path from the file into Picture object

    //    Optional<Picture> picture = this.pictureRepository.findByPath(dto.getProfilePicture());
    //    if(picture.isEmpty()){
    //        return "Invalid User";
    //    }


        User user = this.modelMapper.map(dto, User.class);

      //  user.setProfilePicture(picture.get());


        if(user.getProfilePicture() == null){
            return "Invalid User";
        }

        this.userRepository.save(user);
        return String.format("Successfully imported User: %s", user.getUsername());
    }

    @Override
    public String exportUsersWithTheirPosts() {
        return null;
    }
}
