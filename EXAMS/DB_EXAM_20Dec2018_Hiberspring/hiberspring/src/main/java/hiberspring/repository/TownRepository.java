package hiberspring.repository;

import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    // TODO: Implement me
}
