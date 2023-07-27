package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.PlayerImportDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {


    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/json/players.json";

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validator;
    private final FileUtil fileUtil;


    private final PictureRepository pictureRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, Gson gson, ValidatorUtil validator, FileUtil fileUtil, PictureRepository pictureRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public String importPlayers() throws IOException {
        String json = this.readPlayersJsonFile();
       // System.out.println(json);
        PlayerImportDTO[] importDTOs = this.gson.fromJson(json, PlayerImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(PlayerImportDTO dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid player";
        }

  //      Optional<Player> optPlayer = this.playerRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
        Optional<Player> optPlayer = this.playerRepository.findByFirstNameAndLastNameAndNumberAndSalaryAndPictureUrlAndTeamNameAndTeamPictureUrl(
                dto.getFirstName(), dto.getLastName(), dto.getNumber(), dto.getSalary(), dto.getPicture().getUrl(), dto.getTeam().getName(), dto.getTeam().getPicture().getUrl());


        if (optPlayer.isPresent()) {
            return "Invalid player";
        }

        Player player= this.modelMapper.map(dto, Player.class);

        //// SET Picture
        Optional<Picture> picture = pictureRepository.findByUrl(dto.getPicture().getUrl());
        if(picture.isEmpty()){
            return "Invalid player";
        }
        player.setPicture(picture.get());
        /////////////////////////


        //// SET TEAM
        Optional<Team> team = teamRepository.findByNameAndPictureUrl(dto.getTeam().getName(), dto.getTeam().getPicture().getUrl());
        if(team.isEmpty()){
            return "Invalid player";
        }
        player.setTeam(team.get());
        /////////////////////////


        this.playerRepository.save(player);
        return "Successfully imported player " + player.getFirstName() + " " + player.getLastName();
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return fileUtil.readFile(PLAYERS_FILE_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        List<Player> players = playerRepository.findAllBySalaryGreaterThan(BigDecimal.valueOf(100000));

        StringBuilder sb = new StringBuilder();

            players
                .forEach(player -> {
                    String playerInfo = String.format("Player name: %s %s\n" +
                            "Number: %d\n" +
                            "Salary: %.2f\n" +
                            "Team: %s\n", player.getFirstName(), player.getLastName(), player.getNumber(),
                            player.getSalary(), player.getTeam().getName());
                    sb.append(playerInfo);
                });
       // Removal of last line separator, if that is necessary
       // return (sb).substring(0, sb.toString().length()-1);
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        List<Player> players = playerRepository.findAllByTeamNameOrderById("North Hub");

        StringBuilder sb = new StringBuilder("Team: North Hub\n");

        String playersInfo = players.stream()
                .map(player -> {
                    return String.format("Player name: %s %s - %s\n" +
                            "Number: %d", player.getFirstName(), player.getLastName(), player.getPosition(), player.getNumber());
                })
                .collect(Collectors.joining("\n"));

        return (sb.append(playersInfo)).toString();
    }
}
