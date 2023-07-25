package softuni.exam.models.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {
    @NotNull
    private String guide;
    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Positive
    private int population;

    public TownImportDTO() {
    }

    public String getGuide() {
        return guide;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
