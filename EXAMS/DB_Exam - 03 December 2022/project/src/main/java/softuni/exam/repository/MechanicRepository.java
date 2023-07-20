package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Mechanic;

import java.util.Optional;

// TODO:
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {


    Optional<Mechanic> findByEmail(String email);

    Optional<Mechanic> findByFirstName(String firstName);
}
