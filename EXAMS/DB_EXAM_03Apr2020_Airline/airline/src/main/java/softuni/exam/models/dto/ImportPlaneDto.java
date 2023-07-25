package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlaneDto {

    @Size(min = 5)
    @NotNull
    @XmlElement(name = "register-number")
    private String registerNumber;
    @Positive
    @NotNull
    private int capacity;
    @Size(min = 2)
    @NotNull
    private String airline;

    public ImportPlaneDto() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAirline() {
        return airline;
    }


}
