package hiberspring.repository;

import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByNameAndClientsAndBranchName(String name, int clients, String branch);

}
