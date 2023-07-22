package softuni.exam.models.dto;


import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {



    @Size(min = 2)
    @NotNull
    //@UniqueElements
    private String townName;
    @Positive
    @NotNull
    private int population;

    public TownImportDTO() {
    }

    public String getTownName() {
        return townName;
    }

    public int getPopulation() {
        return population;
    }
}
