package softuni.exam.instagraphlite.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {
    private final PictureService pictureService;
    private final UserService userService;
    private final PostService postService;


    @Autowired
    public ImportController(PictureService pictureService, UserService userService, PostService postService) {
        this.pictureService = pictureService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/json")
    public ModelAndView importJson() {
        boolean[] areImported = new boolean[]{
                this.pictureService.areImported(),
                this.userService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }

    @GetMapping("/pictures")
    public ModelAndView importPicture() throws IOException {
        String picturesFromJson = this.pictureService.readFromFileContent();
        return super.view("json/import-pictures","pictures",picturesFromJson);
    }
    @PostMapping("/pictures")
    public ModelAndView importPicturesConfirm() throws IOException {
        System.out.println(this.pictureService.importPictures());
        return super.redirect("/import/json");
    }
    @GetMapping("/users")
    public ModelAndView importUsers() throws IOException {
        String usersFromJson = this.userService.readFromFileContent();
        return super.view("json/import-users","users",usersFromJson);
    }
    @PostMapping("/users")
    public ModelAndView importUsersConfirm() throws IOException {
        System.out.println(this.userService.importUsers());

        return super.redirect("/import/json");
    }
    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
               this.postService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }
    @GetMapping("/posts")
    public ModelAndView importPost() throws IOException {
        String postsFromXml = this.postService.readFromFileContent();
        return super.view("xml/import-posts","posts",postsFromXml);
    }
    @PostMapping("/posts")
    public ModelAndView importPostsConfirm() throws IOException, JAXBException {
        System.out.println(this.postService.importPosts());

        return super.redirect("/import/xml");
    }
}
