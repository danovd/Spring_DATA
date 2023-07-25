package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTicketRootDTO {


    @XmlElement(name = "ticket")
    private List<ImportTicketDto> tickets;


    public ImportTicketRootDTO() {
    }

    public List<ImportTicketDto> getTickets() {
        return tickets;
    }
}
