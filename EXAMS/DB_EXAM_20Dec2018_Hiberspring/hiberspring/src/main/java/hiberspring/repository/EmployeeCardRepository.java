package hiberspring.repository;

import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long> {
    // TODO: Implement me
}
