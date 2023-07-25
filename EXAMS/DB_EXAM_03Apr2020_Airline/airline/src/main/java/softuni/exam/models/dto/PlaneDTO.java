package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneDTO {

    @XmlElement(name = "register-number")
    @NotNull
    private String registerNumber;

    public PlaneDTO() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }
}
