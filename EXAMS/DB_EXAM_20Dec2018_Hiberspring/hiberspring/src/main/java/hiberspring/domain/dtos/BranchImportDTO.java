package hiberspring.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class BranchImportDTO {

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("town")
    private String town;

    public BranchImportDTO() {
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }
}
