package softuni.exam.service;

import java.io.IOException;

public interface PlayerService {
    String importPlayers();

    boolean areImported();

    String readPlayersJsonFile() throws IOException;

    String exportPlayersWhereSalaryBiggerThan();

    String exportPlayersInATeam();
}
