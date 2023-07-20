package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CitySeedDto {

    @Expose
//    @SerializedName("city_name")
    private String cityName;
    @Expose
    private Long country;
    @Expose
    private Integer population;
    @Expose
    private String description;

    @Size(min = 2, max = 60)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public Long getCountry() {
        return country;
    }

    public CitySeedDto setCountry(Long country) {
        this.country = country;
        return this;
    }


    @Min(500)
    public Integer getPopulation() {
        return population;
    }

    public CitySeedDto setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    @Size(min = 2)
    public String getDescription() {
        return description;
    }

    public CitySeedDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
