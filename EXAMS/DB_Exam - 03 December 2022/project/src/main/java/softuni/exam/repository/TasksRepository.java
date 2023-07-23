package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.CarType;
import softuni.exam.models.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface TasksRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByDate(LocalDateTime date);

    List<Task> findByCarCarTypeOrderByPriceDesc(CarType carType);
}
