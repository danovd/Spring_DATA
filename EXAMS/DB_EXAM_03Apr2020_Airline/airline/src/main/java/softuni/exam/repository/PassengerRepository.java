package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Passenger;

import java.util.Optional;

@Repository
public interface PassengerRepository  extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findByEmail(String email);
}
