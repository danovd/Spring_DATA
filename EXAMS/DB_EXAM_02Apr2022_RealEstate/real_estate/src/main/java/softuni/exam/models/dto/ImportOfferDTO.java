package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {

    @Positive
    @NotNull
    private BigDecimal price;


    @NotNull
    private AgentDTO agent;

    @NotNull
    private ApartmentDTO apartment;

    @NotNull
    private String publishedOn;


    public ImportOfferDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public ApartmentDTO getApartment() {
        return apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }
}
