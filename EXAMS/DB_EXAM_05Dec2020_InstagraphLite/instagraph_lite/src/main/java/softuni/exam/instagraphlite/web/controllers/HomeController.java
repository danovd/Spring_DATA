package softuni.exam.instagraphlite.web.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;

@Controller
public class HomeController extends BaseController {

    private final PictureService pictureService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public HomeController(PictureService pictureService, UserService userService, PostService postService) {
        this.pictureService = pictureService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.pictureService.areImported() && this.userService.areImported() && this.postService.areImported();

        return super.view("index", "areImported", areImported);
    }
}
