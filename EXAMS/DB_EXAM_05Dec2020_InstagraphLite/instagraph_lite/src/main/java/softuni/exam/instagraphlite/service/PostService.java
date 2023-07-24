package softuni.exam.instagraphlite.service;

import java.io.IOException;

public interface PostService {
    boolean areImported();
    String readFromFileContent() throws IOException;
    String importPosts() throws IOException;

}
