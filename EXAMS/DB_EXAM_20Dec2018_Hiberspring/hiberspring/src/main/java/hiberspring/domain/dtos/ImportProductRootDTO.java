package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductRootDTO {
    @XmlElement(name = "product")
    private List<ImportProductDto> products;

    public ImportProductRootDTO() {
    }

    public List<ImportProductDto> getProducts() {
        return products;
    }
}
