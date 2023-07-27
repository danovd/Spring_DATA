package softuni.exam.domain.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTeamDto {
    @Size(min = 3, max = 20)
    @NotNull
    private String name;

    @NotNull
    private ImportPictureDto picture;

    public ImportTeamDto() {
    }

    public String getName() {
        return name;
    }

    public ImportPictureDto getPicture() {
        return picture;
    }
}
