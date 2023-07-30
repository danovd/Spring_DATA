package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private double lightYears;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StarType starType;

    @ManyToOne
    @JoinColumn(name = "constellation_id", referencedColumnName = "id")
    private Constellation constellation;



    @OneToMany(mappedBy = "observingStar", targetEntity = Astronomer.class,
    fetch = FetchType.EAGER)
    private Set<Astronomer> observers;

    public Star() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    public Set<Astronomer> getAstronomers() {
        return observers;
    }

    public void setAstronomers(Set<Astronomer> astronomers) {
        this.observers = astronomers;
    }


    @Override
    public String toString() {

        return String.format("Star: %s\n" +
                "   *Distance: %.2f light years\n" +
                "   **Description: %s\n" +
                "   ***Constellation: %s",
                this.getName(),
                this.getLightYears(),
                this.getDescription(),
                this.getConstellation().getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return id == star.id && Objects.equals(name, star.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


}
