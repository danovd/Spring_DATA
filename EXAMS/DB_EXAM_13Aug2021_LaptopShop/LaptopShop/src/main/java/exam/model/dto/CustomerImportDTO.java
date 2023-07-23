package exam.model.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;



public class CustomerImportDTO {

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Email
    private String email;

    private String registeredOn;


    private TownDTO town;


    public CustomerImportDTO() {
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

    public String getRegisteredOn() {
        return registeredOn;
    }

    public TownDTO getTown() {
        return town;
    }
}
