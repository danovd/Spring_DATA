package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

@Column(name = "country_name", unique = true, nullable = false)
private String countryName;
@Column(name = "currency", nullable = false)
private String currency;


    public Country() {
    }

    public String getName() {
        return countryName;
    }

    public void setName(String name) {
        this.countryName = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}





























