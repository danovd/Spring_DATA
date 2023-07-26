package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class CarImportDTO {
    @NotNull
    @Positive
 //   @JsonProperty("kilometers")
    private int kilometers;
    @NotNull
    @Size(min = 2, max = 19)
    //   @JsonProperty("make")
    private String make;

    @NotNull
    @Size(min = 2, max = 19)
    //   @JsonProperty("model")
    private String model;

    @NotNull
    //   @JsonProperty("registeredOn")
    private String registeredOn;

    public CarImportDTO() {
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }
}
