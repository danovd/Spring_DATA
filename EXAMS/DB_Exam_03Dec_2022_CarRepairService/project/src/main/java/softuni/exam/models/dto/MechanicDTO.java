package softuni.exam.models.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MechanicDTO {

    @Size(min = 2)
    private String firstName;


    public String getFirstName() {
        return this.firstName;
    }
}
