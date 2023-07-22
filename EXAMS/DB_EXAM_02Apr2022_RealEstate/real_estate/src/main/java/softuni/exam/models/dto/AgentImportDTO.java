package softuni.exam.models.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AgentImportDTO {



    @Size(min = 2)
    @NotNull
    //@UniqueElements
    private String firstName;
    @Size(min = 2)
    @NotNull
    private String lastName;
    @NotNull
    private String town;
    @Email
    @NotNull
    private String email;

    public AgentImportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTown() {
        return town;
    }

    public String getEmail() {
        return email;
    }
}
