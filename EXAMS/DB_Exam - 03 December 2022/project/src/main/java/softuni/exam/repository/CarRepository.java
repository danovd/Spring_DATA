package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Car;

import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByPlateNumber(String plateNumber);

   Optional<Car> findById(Long id);
}
