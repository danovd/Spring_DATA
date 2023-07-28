package com.example.football.web.controllers;

import com.example.football.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final TownService townService;
    private final TeamService teamService;
    private final StatService statService;
    private final PlayerService playerService;

    public ImportController(TownService townService, TeamService teamService, StatService statService, PlayerService playerService) {
        this.townService = townService;
        this.teamService = teamService;
        this.statService = statService;
        this.playerService = playerService;
    }


    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.teamService.areImported(),
                this.townService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }


    @GetMapping("/xml")
    public ModelAndView importXml() {
        boolean[] areImported = new boolean[]{
                this.statService.areImported(),
                this.playerService.areImported()
        };

        return super.view("xml/import-xml", "areImported", areImported);
    }


    @GetMapping("/stats")
    public ModelAndView importStats() throws IOException {
        String fileContent = this.statService.readStatsFileContent();
        return super.view("xml/import-stats", "stats", fileContent);
    }

    @PostMapping("/stats")
    public ModelAndView importStatsConfirm() throws JAXBException, IOException {
        System.out.println(this.statService.importStats());

        return super.redirect("/import/xml");
    }

    @GetMapping("/players")
    public ModelAndView importPlayers() throws IOException {
        String fileContent = this.playerService.readPlayersFileContent();

        return super.view("xml/import-players", "players", fileContent);
    }

    @PostMapping("/players")
    public ModelAndView importPlayersConfirm() throws JAXBException, FileNotFoundException, IOException {
        System.out.println(this.playerService.importPlayers());

        return super.redirect("/import/xml");
    }

    @GetMapping("/towns")
    public ModelAndView importTowns() throws IOException {
        String fileContent = this.townService.readTownsFileContent();

        return super.view("json/import-towns", "towns", fileContent);
    }

    @PostMapping("/towns")
    public ModelAndView importTownsConfirm() throws IOException {
        System.out.println(this.townService.importTowns());
        return super.redirect("/import/json");
    }

    @GetMapping("/teams")
    public ModelAndView importTeams() throws IOException {
        String fileContent = this.teamService.readTeamsFileContent();

        return super.view("json/import-teams", "teams", fileContent);
    }

    @PostMapping("/teams")
    public ModelAndView importTeamsConfirm() throws IOException, JAXBException {
        System.out.println(this.teamService.importTeams());
        return super.redirect("/import/json");
    }
}
