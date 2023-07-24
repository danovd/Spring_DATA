package softuni.exam.instagraphlite.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {
    private final UserService userService;
    private final PictureService pictureService;

    @Autowired
    public ExportController(UserService userService, PictureService pictureService) {
        this.userService = userService;
        this.pictureService = pictureService;
    }

    @GetMapping("/exportUsers")
    public ModelAndView exportUsersWithPosts(){
        String exportResult = this.userService.exportUsersWithTheirPosts();
        return super.view("/export/export-users","exportUsers",exportResult);

    }

    @GetMapping("/exportPictures")
    public ModelAndView exportPictures(){
        String exportResult = this.pictureService.exportPictures();
        return super.view("/export/export-pictures","exportPictures",exportResult);

    }

}
