package softuni.exam.models.entity;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  //  @Size(min = 2, max = 20)
    private String firstName;
  //  @Size(min = 2, max = 20)
    private String lastName;
    @Email
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @NotNull
    private String town;


}
