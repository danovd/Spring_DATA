package softuni.exam.instagraphlite.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.service.PostService;

import java.io.IOException;


@Service
public class PostServiceImpl implements PostService {
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return null;
    }

    @Override
    public String importPosts() throws IOException {
        return null;
    }
}
