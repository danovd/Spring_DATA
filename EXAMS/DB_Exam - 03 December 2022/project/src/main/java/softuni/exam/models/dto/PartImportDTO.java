package softuni.exam.models.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDTO {
    @Size(min = 2, max = 19)
    private String partName;

    @DecimalMin("10")
    @DecimalMax("2000")
    private double price;

    @Positive
    private int quantity;

    public String getPartName() {
        return partName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
