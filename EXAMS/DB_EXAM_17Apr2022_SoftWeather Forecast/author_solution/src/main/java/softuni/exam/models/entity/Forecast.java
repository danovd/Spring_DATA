package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.DaysOfWeek;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity {
    private Double minTemperature;
    private Double maxTemperature;
    private DaysOfWeek dayOfWeek;
    private City city;
    private LocalTime sunrise;
    private LocalTime sunset;


    @Column(nullable = false)
    public Double getMinTemperature() {
        return minTemperature;
    }

    public Forecast setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
        return this;
    }

    @Column(nullable = false)
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Forecast setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public DaysOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Forecast setDayOfWeek(DaysOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    @ManyToOne
    public City getCity() {
        return city;
    }

    public Forecast setCity(City city) {
        this.city = city;
        return this;
    }

    @Column(nullable = false)
    public LocalTime getSunrise() {
        return sunrise;
    }

    public Forecast setSunrise(LocalTime localTime) {
        this.sunrise = localTime;
        return this;
    }

    @Column(nullable = false)
    public LocalTime getSunset() {
        return sunset;
    }

    public Forecast setSunset(LocalTime localTime) {
        this.sunset = localTime;
        return this;
    }
}
