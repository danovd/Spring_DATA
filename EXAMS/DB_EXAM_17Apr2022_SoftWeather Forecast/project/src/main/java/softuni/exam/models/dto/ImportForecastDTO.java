package softuni.exam.models.dto;

import softuni.exam.models.entity.DayOfWeek;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;



@XmlAccessorType(XmlAccessType.FIELD)
public class ImportForecastDTO {
    @NotNull
    @XmlElement(name = "day_of_week")
    private DayOfWeek dayOfWeek;
    @DecimalMin("-20")
    @DecimalMax("60")
    @NotNull
    @XmlElement(name = "max_temperature")
    private float maxTemperature;
    @DecimalMin(value = "-50")
    @DecimalMax(value = "40")
    @NotNull
    @XmlElement(name = "min_temperature")
    private float minTemperature;

    private String sunrise;
    private String sunset;

    private Long city;


    public ImportForecastDTO() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
    public Long getCity() {
        return city;
    }


    @Override
    public String toString() {
        return "ImportForecastDTO{" +
                "dayOfWeek=" + dayOfWeek +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", city=" + city +
                '}';
    }
}
