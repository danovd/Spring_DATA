package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




}
