package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class PostServiceImpl implements PostService {
    private static final String POSTS_FILE_PATH = "src/main/resources/files/posts.xml";
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
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
    public String importPosts() throws IOException {
        return null;
    }
}
