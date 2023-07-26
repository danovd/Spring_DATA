package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSellerRootDTO {
    @XmlElement(name = "seller")
    private List<ImportSellerDto> sellers;

    public ImportSellerRootDTO() {
    }

    public List<ImportSellerDto> getSellers() {
        return sellers;
    }
}
