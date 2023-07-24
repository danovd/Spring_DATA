package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PictureDTO {
    @NotNull
    private String path;


    public PictureDTO() {
    }

    public String getPath() {
        return path;
    }
}
