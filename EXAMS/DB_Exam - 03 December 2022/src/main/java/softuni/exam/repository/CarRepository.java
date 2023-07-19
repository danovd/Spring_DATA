package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Car;

// TODO:
public interface CarRepository extends JpaRepository<Car, Long> {

}
