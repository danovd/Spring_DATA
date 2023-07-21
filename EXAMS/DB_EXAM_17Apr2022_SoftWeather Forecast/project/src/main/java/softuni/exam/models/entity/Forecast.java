package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "max_temperature", nullable = false)
    private float maxTemperature;
    @Column(name = "min_temperature", nullable = false)
    private float minTemperature;


@Column(nullable = false)
    private LocalTime sunrise;
    @Column(nullable = false)
    private LocalTime sunset;
    @ManyToOne(targetEntity = City.class)
   // @JoinColumn(name = "city_id")
    private City city;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Forecast() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }


    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", city=" + city +
                '}';
    }
}














































