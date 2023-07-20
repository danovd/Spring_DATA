package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softuni.exam.models.entity.CarType;
import softuni.exam.models.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByDate(LocalDateTime date);

    List<Task> findByCarCarTypeOrderByPriceDesc(CarType carType);
}
