package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.util.FileUtil;

import javax.validation.Validator;

@Service
public class PlayerServiceImpl implements PlayerService {


    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/json/players.json";

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;
    private final FileUtil fileUtil;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, Gson gson, Validator validator, FileUtil fileUtil) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importPlayers() {
        //TODO Implement me
        return "";
    }

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPlayersJsonFile()  {
        return "";
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        //TODO Implement me
        return "";
    }

    @Override
    public String exportPlayersInATeam() {
        //TODO Implement me
        return "";
    }
}
