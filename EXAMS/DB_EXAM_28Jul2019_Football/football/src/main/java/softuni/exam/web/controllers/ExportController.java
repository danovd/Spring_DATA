package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.PlayerService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final PlayerService playerService;

    @Autowired
    public ExportController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players-with-salary-bigger")
    public ModelAndView exportPlayerWithSalaryBiggerThan(){
        String playersWithSalary = this.playerService.exportPlayersWhereSalaryBiggerThan();

        return super.view("export/export-players-with-salary-bigger.html","playersWithSalaryBiggerThan", playersWithSalary);
    }

    @GetMapping("/team-players")
    public ModelAndView exportPlayerInATeam(){
        String playersInATeam = this.playerService.exportPlayersInATeam();

        return super.view("export/export-team-players.html","playersInATeam", playersInATeam);
    }
}
