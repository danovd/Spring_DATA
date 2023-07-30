package softuni.exam.models.dto;

import softuni.exam.models.entity.StarType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class StarImportDTO {
    @Size(min = 6)
    @NotNull
    private String description;
    @DecimalMin("0.00")
    @NotNull
    private double lightYears;
    @Size(min = 2, max = 30)
    @NotNull
    private String name;
    @NotNull
    private StarType starType;
    @NotNull
    private Long constellation;

    public StarImportDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarType getStarType() {
        return starType;
    }

    public void setStarType(StarType starType) {
        this.starType = starType;
    }

    public Long getConstellation() {
        return constellation;
    }

    public void setConstellation(Long constellation) {
        this.constellation = constellation;
    }
}
