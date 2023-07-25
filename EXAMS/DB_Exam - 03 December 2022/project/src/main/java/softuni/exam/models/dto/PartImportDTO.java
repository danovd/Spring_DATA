package softuni.exam.models.dto;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDTO {
    @Size(min = 2, max = 19)
    @NotNull
    private String partName;

    @DecimalMin("10")
    @DecimalMax("2000")
    @NotNull
    private double price;

    @Positive
    @NotNull
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
