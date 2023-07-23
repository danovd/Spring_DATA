package exam.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportShopDto {

    @Size(min = 4)
    private String address;
    @Min(1)
    @Max(50)
    @XmlElement(name = "employee-count")
    private int employeeCount;
    @DecimalMin("20000")
    private BigDecimal income;
    @Size(min = 4)
    private String name;

    @Min(150)
    @XmlElement(name = "shop-area")
    private int shopArea;
    @XmlElement(name = "town")
    private TownDTO town;


    public ImportShopDto() {
    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public TownDTO getTown() {
        return town;
    }
}
