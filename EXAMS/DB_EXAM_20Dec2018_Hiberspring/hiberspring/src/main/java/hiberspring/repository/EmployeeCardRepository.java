package hiberspring.repository;

import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long> {
    Optional<EmployeeCard> findByNumber(String number);

}
