package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
@Column(name = "city_name", unique = true, nullable = true)
private String cityName;


@Column(name = "description", columnDefinition = "TEXT")
private String description;

@Column(nullable = false)
private int population;


@ManyToOne(targetEntity = Country.class)
//@JoinColumn(name = "country_id")
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

    @Override
    public String toString() {
        return "City{" +
                "id=" + this.getId() +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                ", population=" + population +
                ", country=" + country +
                '}';
    }
}















































