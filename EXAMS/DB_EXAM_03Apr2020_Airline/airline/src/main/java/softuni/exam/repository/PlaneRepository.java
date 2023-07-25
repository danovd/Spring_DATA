package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Plane;

import java.util.Optional;

@Repository

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    Optional<Plane> findByRegisterNumber(String registerNumber);
}
