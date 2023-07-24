package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO {


    @NotNull
    private String username;


    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }
}
