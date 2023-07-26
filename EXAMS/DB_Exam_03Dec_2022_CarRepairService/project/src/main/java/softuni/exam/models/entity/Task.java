package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;


    @Column(nullable = false)
    private LocalDateTime date;


    @ManyToOne
  //  @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @ManyToOne
  //  @JoinColumn(name = "cars_id")
    private Car car;

    @ManyToOne
  //  @JoinColumn(name = "parts_id")
    private Part part;


    public Task() {
    }

    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Car getCar() {
        return car;
    }

    public Part getPart() {
        return part;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setPart(Part part) {
        this.part = part;
    }


    @Override
    public String toString() {
        return String.format("Car %s %s with %dkm\n                            " +
                "--Mechanic: %s %s - task №%d:--\n                            " +
                "--Engine: %s\n" +
                "                            ---Price: %.2f$", this.car.getCarMake(), this.car.getCarModel(), this.car.getKilometers(),
                this.mechanic.getFirstName(), this.mechanic.getLastName(), this.id,
                this.car.getEngine(), this.price);

    }
}
