package hiberspring.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
