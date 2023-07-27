package softuni.exam.domain.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPictureDto {

    @NotNull
    private String url;

    public ImportPictureDto() {
    }

    public String getUrl() {
        return url;
    }
}
