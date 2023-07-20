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
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "cars_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "parts_id")
    private Part part;


    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
