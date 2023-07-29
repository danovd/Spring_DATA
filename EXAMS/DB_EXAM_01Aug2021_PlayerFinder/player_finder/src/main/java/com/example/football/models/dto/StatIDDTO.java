package com.example.football.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatIDDTO {

    @NotNull
    private long id;

    public StatIDDTO() {
    }

    public long getId() {
        return id;
    }
}
