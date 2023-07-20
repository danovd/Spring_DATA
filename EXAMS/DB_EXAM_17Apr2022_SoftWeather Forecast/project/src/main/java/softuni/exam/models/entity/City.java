package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{
@Column(name = "city_name", unique = true, nullable = true)
private String cityName;


@Column(name = "description", columnDefinition = "TEXT")
private String description;

@Column(nullable = false)
private int population;


@ManyToOne
@JoinColumn(name = "country_id")
private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public City() {
    }
}















































