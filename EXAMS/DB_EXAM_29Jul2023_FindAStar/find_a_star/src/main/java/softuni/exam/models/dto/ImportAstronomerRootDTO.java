package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportAstronomerRootDTO {

    @XmlElement(name = "astronomer")
    private List<ImportAstronomerDto> astronomers;

    public ImportAstronomerRootDTO() {
    }

    public List<ImportAstronomerDto> getAstronomers() {
        return astronomers;
    }
}
