package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;

public class TownImportDTO {
    @NotNull
    private String name;
    @NotNull
    private int population;

    public TownImportDTO() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
