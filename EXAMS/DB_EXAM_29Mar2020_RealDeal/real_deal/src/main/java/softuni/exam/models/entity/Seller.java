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


  public Seller() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }
}
