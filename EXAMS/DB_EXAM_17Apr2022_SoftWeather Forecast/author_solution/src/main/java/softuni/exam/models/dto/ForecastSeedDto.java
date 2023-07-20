package softuni.exam.models.dto;

import softuni.exam.models.entity.enums.DaysOfWeek;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedDto {
    @XmlElement(name = "min_temperature")
    private Double minTemperature;
    @XmlElement(name = "max_temperature")
    private Double maxTemperature;
    @XmlElement(name = "day_of_week")
    private DaysOfWeek dayOfWeek;
    @XmlElement(name = "city")
    private Long city;
    @XmlElement(name = "sunrise")
    private String sunrise;
    @XmlElement(name = "sunset")
    private String sunset;



    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    @NotNull
    public Double getMinTemperature() {
        return minTemperature;
    }

    public ForecastSeedDto setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
        return this;
    }

    @DecimalMin(value = "-20")
    @DecimalMax(value = "60")
    @NotNull
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public ForecastSeedDto setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    @NotNull
    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public ForecastSeedDto setDayOfWeek(DaysOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Long getCity() {
        return city;
    }

    public ForecastSeedDto setCity(Long city) {
        this.city = city;
        return this;
    }

    @NotNull
    public String getSunrise() {
        return sunrise;
    }

    public ForecastSeedDto setSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    @NotNull
    public String getSunset() {
        return sunset;
    }

    public ForecastSeedDto setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }

   }
