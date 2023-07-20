package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    private String cityName;
    private Integer population;
    private Country country;
    private String description;

    @Column(unique = true, nullable = false)
    public String getCityName() {
        return cityName;
    }

    public City setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    @Column(nullable = false)
    public Integer getPopulation() {
        return population;
    }

    public City setPopulation(Integer population) {
        this.population = population;
        return this;
    }


    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public City setCountry(Country country) {
        this.country = country;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public City setDescription(String description) {
        this.description = description;
        return this;
    }
}
