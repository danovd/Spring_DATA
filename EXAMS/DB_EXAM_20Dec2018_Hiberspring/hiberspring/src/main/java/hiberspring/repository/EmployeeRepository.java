package hiberspring.repository;

import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // TODO: Implement me
}
