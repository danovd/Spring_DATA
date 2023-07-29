package com.example.football.models.dto;

import com.example.football.models.entity.PlayerPosition;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlayerDto {
    @Size(min = 3)
    @XmlElement(name = "first-name")
    @NotNull
    private String firstName;
    @Size(min = 3)
    @XmlElement(name = "last-name")
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @XmlElement(name = "birth-date")
    @NotNull
    private String birthDate;
    @NotNull
    private PlayerPosition position;
    @NotNull
    private StatIDDTO stat;
    @NotNull
    private NameDTO team;
    @NotNull
    private NameDTO town;

    public ImportPlayerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public StatIDDTO getStat() {
        return stat;
    }

    public NameDTO getTeam() {
        return team;
    }

    public NameDTO getTown() {
        return town;
    }
}
