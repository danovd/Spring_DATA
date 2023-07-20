package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    private String countryName;
    private String currency;

    @Column(unique = true, nullable = false)
    public String getCountryName() {
        return countryName;
    }

    public Country setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    @Column(nullable = false)
    public String getCurrency() {
        return currency;
    }

    public Country setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
