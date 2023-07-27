package softuni.exam.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTeamRootDTO {
    @XmlElement(name = "team")
    private List<ImportTeamDto> teams;

    public ImportTeamRootDTO() {
    }

    public List<ImportTeamDto> getTeams() {
        return teams;
    }
}
