package softuni.exam.models.dto;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AgentDTO {


    @XmlElement(name = "name")
    @NotNull
    private String name;


    public AgentDTO() {
    }

    public String getName() {
        return name;
    }
}
