package softuni.exam.models.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDto {
    @XmlElement(name ="added-on")
    private String addedOn;

   // @Column(columnDefinition = "TEXT")
    @Size(min = 5)
    private String description;
    @XmlElement(name = "has-gold-status")
    private boolean hasGoldStatus;
    @Positive
    private BigDecimal price;

    @NotNull
    private IdDTO seller;
    @NotNull
    private IdDTO car;


    public ImportOfferDto() {
    }

    public String getAddedOn() {
        return addedOn;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public IdDTO getSeller() {
        return seller;
    }

    public IdDTO getCar() {
        return car;
    }
}
