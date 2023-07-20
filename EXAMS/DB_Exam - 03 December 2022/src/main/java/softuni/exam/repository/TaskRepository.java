package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
