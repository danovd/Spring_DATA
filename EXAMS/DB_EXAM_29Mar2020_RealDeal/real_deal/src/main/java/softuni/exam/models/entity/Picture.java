package softuni.exam.models.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
  //  @Size(min = 2, max = 20)
    @NotNull
    private String name;

   // @NotNull
    private LocalDateTime dateAndTime;
    @ManyToOne
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return id == picture.id && Objects.equals(name, picture.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
