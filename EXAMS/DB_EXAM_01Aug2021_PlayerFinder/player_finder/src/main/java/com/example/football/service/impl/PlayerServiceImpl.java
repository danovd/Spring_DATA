package com.example.football.service.impl;

import com.example.football.models.dto.ImportPlayerDto;
import com.example.football.models.dto.ImportPlayerRootDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.FileUtil;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.football.models.entity.Town;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;


    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;



    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil, TeamRepository teamRepository, TownRepository townRepository, StatRepository statRepository) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return fileUtil.readFile(PLAYERS_FILE_PATH);
    }

    @Override
    public String importPlayers() throws JAXBException {
        ImportPlayerRootDTO statRootDTOs = this.xmlParser.parseXml(PLAYERS_FILE_PATH, ImportPlayerRootDTO.class);
        return statRootDTOs.getPlayers().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportPlayerDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Player";
        }

        Optional<Player> optPlayer = this.playerRepository.findByEmail(dto.getEmail());


        if (optPlayer.isPresent()) {
            return "Invalid Player";
        }


        Player player = this.modelMapper.map(dto, Player.class);

        /// Find Town, Team and Stat. They are all valid as a condition!
        // Anyway we could add some checks, if necessary!
        Optional<Town> town = this.townRepository.findByName(dto.getTown().getName());
        Optional<Team> team = this.teamRepository.findByName(dto.getTeam().getName());
        Optional<Stat> stat = this.statRepository.findById(dto.getStat().getId());
        ///
        if(town.isEmpty() || team.isEmpty() || stat.isEmpty()){
            return "Invalid Player";
        }
        /// SET town, team, stat to player
        player.setTown(town.get());
        player.setTeam(team.get());
        player.setStat(stat.get());
        ///

        /// Ultimately save to DB
        this.playerRepository.save(player);

        //  Има разминаване в условието - веднъж се посочва шаблон, според който между firstName и lastName
        // при извеждане на резултата има тире, докато в примерните резултати е само спейс!
        return"Successfully imported Player " + player.getFirstName() + " " + player.getLastName() + " - " + player.getPosition();
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}
