package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class IdDTO {

    @NotNull
    private Long id;

    public IdDTO() {
    }

    public Long getId() {
        return id;
    }
}
