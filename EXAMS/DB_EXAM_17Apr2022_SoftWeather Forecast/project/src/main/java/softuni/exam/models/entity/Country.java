package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{
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
}





























