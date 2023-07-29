package com.example.football.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TeamImportDTO {
    @Min(1000)
    @NotNull
    private int fanBase;
    @Size(min = 10)
    @NotNull
    private String history;
    @Size(min = 3)
    @NotNull
    private String name;
    @Size(min = 3)
    @NotNull
    private String stadiumName;
    ///  ВНИМАНИЕ!! НЕ @JsonProperty("townName"), а @SerializedName("townName")
    @SerializedName("townName")
    @NotNull
    private String town;

    public TeamImportDTO() {
    }

    public int getFanBase() {
        return fanBase;
    }

    public String getHistory() {
        return history;
    }

    public String getName() {
        return name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getTown() {
        return town;
    }

    /*@Override
    public String toString() {
        return "TeamImportDTO{" +
                "fanBase=" + fanBase +
                ", history='" + history + '\'' +
                ", name='" + name + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", town='" + town + '\'' +
                '}';
    }*/
}
