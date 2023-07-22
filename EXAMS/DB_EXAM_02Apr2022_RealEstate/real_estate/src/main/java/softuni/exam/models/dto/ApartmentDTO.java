package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentDTO {

    private Long id;


    public ApartmentDTO() {
    }

    public Long getId() {
        return id;
    }
}
