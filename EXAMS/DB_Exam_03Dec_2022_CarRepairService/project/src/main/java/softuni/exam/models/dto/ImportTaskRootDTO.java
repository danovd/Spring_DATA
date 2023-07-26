package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;
@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTaskRootDTO {
@XmlElement(name = "task")
List<ImportTaskDTO> tasks;

    public ImportTaskRootDTO() {
    }

    public List<ImportTaskDTO> getTasks() {
        return tasks;
    }
}
