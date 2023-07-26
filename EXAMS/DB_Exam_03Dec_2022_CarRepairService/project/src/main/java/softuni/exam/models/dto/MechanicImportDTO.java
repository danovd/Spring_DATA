package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MechanicImportDTO {
    @Email
    @NotNull
    private String email;
    @Size(min = 2)
    @NotNull
    private String firstName;
    @Size(min = 2)
    @NotNull
    private String lastName;
    @Size(min = 2)
    private String phone;

    public MechanicImportDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
}
