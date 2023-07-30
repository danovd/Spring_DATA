package softuni.exam.models.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConstellationImportDTO {
    @Size(min = 5)
    @NotNull
    private String description;
    @Size(min = 3, max = 20)
    @NotNull
    private String name;


    public ConstellationImportDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
