package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerDTO {
    @Email
    @NotNull
    private String email;

    public PassengerDTO() {
    }

    public String getEmail() {
        return email;
    }
}
