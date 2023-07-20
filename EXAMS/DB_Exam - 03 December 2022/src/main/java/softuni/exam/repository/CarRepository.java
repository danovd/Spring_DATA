package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
