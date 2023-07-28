package hiberspring.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;



import javax.validation.constraints.*;

public class EmployeeCardImportDTO {

    @NotNull
    @JsonProperty("number")
    private String number;

    public EmployeeCardImportDTO() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
