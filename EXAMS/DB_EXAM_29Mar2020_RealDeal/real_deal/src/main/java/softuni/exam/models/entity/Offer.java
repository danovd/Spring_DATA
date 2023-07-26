package softuni.exam.models.entity;



import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime addedOn;
    @Column(columnDefinition = "TEXT")
    @Size(min = 5)
    private String description;

    private boolean hasGoldStatus;
    @Positive
    private BigDecimal price;

    @ManyToOne
    private Seller seller;
    @ManyToOne
    private Car car;
    @ManyToMany
    private Set<Picture> pictures;

    public Offer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (!this.description.equals(offer.description)) return false;
        return this.addedOn.equals(offer.addedOn);
    }

    @Override
    public int hashCode() {
        int result = this.description.hashCode();
        int MULTIPLIER_AUXILIARY_NUMBER_FOR_BETTER_DISTRIBUTION_OF_HASH = 31;

       /* The choice of using the constant value 31 in the hash code calculation is a common practice
        in Java and other programming languages. The number 31 is chosen because it is an odd prime number
        and has some desirable properties when used in hash code calculations.

        Multiplying by a prime number like 31 helps to distribute the hash codes more evenly across the
        available range of integers. This reduces the likelihood of hash code collisions, where two different
        objects end up having the same hash code. By using a prime number like 31, the likelihood of such
        collisions is reduced, increasing the efficiency and performance of hash-based data structures
        like HashMaps or HashSets.*/

        result = MULTIPLIER_AUXILIARY_NUMBER_FOR_BETTER_DISTRIBUTION_OF_HASH * result + this.addedOn.hashCode();
        return result;
    }
}
