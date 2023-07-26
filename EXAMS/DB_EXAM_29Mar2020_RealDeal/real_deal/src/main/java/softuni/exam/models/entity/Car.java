package softuni.exam.models.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Positive
    private int kilometers;
    @NotNull
 //   @Size(min = 2, max = 20)
    private String make;

    @NotNull
  //  @Size(min = 2, max = 20)
    private String model;

    @NotNull
    private LocalDate registeredOn;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!make.equals(car.make)) return false;
        if (!model.equals(car.model)) return false;
        return kilometers == car.kilometers;
    }

    @Override
    public int hashCode() {
        int result = make.hashCode();
        int MULTIPLIER_AUXILIARY_NUMBER_FOR_BETTER_DISTRIBUTION_OF_HASH = 31;

       /* The choice of using the constant value 31 in the hash code calculation is a common practice
        in Java and other programming languages. The number 31 is chosen because it is an odd prime number
        and has some desirable properties when used in hash code calculations.

        Multiplying by a prime number like 31 helps to distribute the hash codes more evenly across the
        available range of integers. This reduces the likelihood of hash code collisions, where two different
        objects end up having the same hash code. By using a prime number like 31, the likelihood of such
        collisions is reduced, increasing the efficiency and performance of hash-based data structures
        like HashMaps or HashSets.*/

        result = MULTIPLIER_AUXILIARY_NUMBER_FOR_BETTER_DISTRIBUTION_OF_HASH * result + model.hashCode();
        result = result + kilometers;
        return result;
    }

    @OneToMany(mappedBy = "car", targetEntity = Picture.class,fetch = FetchType.EAGER)
    private Set<Picture> pictures;

    public Set<Picture> getPictures() {
        return pictures;
    }

    @Override
    public String toString() {

        return String.format("Car make - %s, model - %s\n" +
                "Kilometers - %d\n" +
                "Registered on - %s\n" +
                "Number of pictures - %d", this.getMake(), this.getModel(), this.getKilometers(),
                this.getRegisteredOn().toString(), this.pictures.size());
    }
}
