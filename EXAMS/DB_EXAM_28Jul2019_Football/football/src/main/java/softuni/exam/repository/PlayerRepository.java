package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository  extends JpaRepository<Player, Long> {

    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Player> findByFirstNameAndLastNameAndNumberAndSalaryAndPictureUrlAndTeamNameAndTeamPictureUrl(String firstName, String lastName, int number, BigDecimal salary, String url, String name, String url1);

    List<Player> findAllByTeamNameOrderById(String northHub);

    List<Player> findAllBySalaryGreaterThan(BigDecimal salary);
}
