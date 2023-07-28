package com.example.football.web.controllers;

import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;
    private final PlayerService playerService;

    public HomeController(TownService townService, TeamService teamService, StatService statService, PlayerService playerService) {
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
        this.playerService = playerService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        boolean areImported = this.townService.areImported() &&
                this.teamService.areImported() &&
                this.statService.areImported() &&
                this.playerService.areImported();

        return super.view("index", "areImported", areImported);
    }
}
