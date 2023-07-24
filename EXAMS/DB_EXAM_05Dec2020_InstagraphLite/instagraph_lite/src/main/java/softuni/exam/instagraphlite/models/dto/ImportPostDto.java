package softuni.exam.instagraphlite.models.dto;

import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPostDto {
    @Size(min = 21)
    @NotNull
    private String caption;

    @NotNull
    private PictureDTO picture;

    @NotNull
    private UserDTO user;

    public ImportPostDto() {
    }

    public String getCaption() {
        return caption;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public UserDTO getUser() {
        return user;
    }
}
