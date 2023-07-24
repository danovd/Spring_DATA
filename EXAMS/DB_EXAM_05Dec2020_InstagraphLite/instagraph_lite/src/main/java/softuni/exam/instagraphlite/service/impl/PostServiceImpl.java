package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.ImportPostDto;
import softuni.exam.instagraphlite.models.dto.ImportPostRootDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    private static final String POSTS_FILE_PATH = "src/main/resources/files/posts.xml";
    private final PostRepository postRepository;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;



    //LOCAL MODEL MAPPER IN THIS CLASS!!!
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    public PostServiceImpl(PostRepository postRepository, PictureRepository pictureRepository, UserRepository userRepository, XmlParser xmlParser, ValidationUtil validator) {
        this.postRepository = postRepository;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        //LOCAL MODEL MAPPER IN THIS CLASS!!!
        this.modelMapper = new ModelMapper();
        this.xmlParser = xmlParser;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POSTS_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        ImportPostRootDTO postRootDTOs = this.xmlParser.fromFile(POSTS_FILE_PATH, ImportPostRootDTO.class);
        return postRootDTOs.getPosts().stream().map(this::importPost).collect(Collectors.joining("\n"));
    }

    private String importPost(ImportPostDto dto) {


        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Post";
        }

        Optional<Post> optPost = this.postRepository.findByUserUsernameAndPicturePath(dto.getUser().getUsername(), dto.getPicture().getPath());


        if (optPost.isPresent()) {
            return "Invalid Post";
        }


        Optional <User> user = this.userRepository.findByUsername(dto.getUser().getUsername());
        Optional <Picture> picture = this.pictureRepository.findByPath(dto.getPicture().getPath());

        Post post = this.modelMapper.map(dto, Post.class);

        if(user.isEmpty() || picture.isEmpty()){
            return "Invalid Post";
        }

        post.setPicture(picture.get());
        post.setUser(user.get());


        this.postRepository.save(post);

        return String.format("Successfully imported Post, made by %s", dto.getUser().getUsername());
    }
}
