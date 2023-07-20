package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softuni.exam.models.entity.Task;

import java.time.LocalDateTime;
import java.util.Optional;

// TODO:
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByDate(LocalDateTime date);
}
