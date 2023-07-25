package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Plane;
import softuni.exam.models.entity.Ticket;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket, Long> {

}
