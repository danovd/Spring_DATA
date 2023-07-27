package softuni.exam.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPictureRootDTO {

    @XmlElement(name = "picture")
    private List<ImportPictureDto> pictures;

    public ImportPictureRootDTO() {
    }

    public List<ImportPictureDto> getPictures() {
        return pictures;
    }
}
