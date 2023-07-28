package hiberspring.repository;

import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFirstNameAndLastNameAndPositionAndEmployeeCardNumberAndBranchName(String firstName, String lastName, String position, String card, String branch);

    Optional<Employee> findByEmployeeCardNumber(String card);
}
