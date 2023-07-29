package com.example.football.models.dto;

import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportStatDto {
    @DecimalMin("0.00")
    private float passing;
    @DecimalMin("0.00")
    private float shooting;
    @DecimalMin("0.00")
    private float endurance;

    public ImportStatDto() {
    }

    public float getPassing() {
        return passing;
    }

    public float getShooting() {
        return shooting;
    }

    public float getEndurance() {
        return endurance;
    }
}
