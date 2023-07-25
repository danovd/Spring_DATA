package softuni.exam.models.dto;


import softuni.exam.models.entity.Town;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTicketDto {

    @Positive
    @NotNull
    private BigDecimal price;

    @XmlElement(name = "serial-number")
    @Size(min = 2)
    @NotNull
    private String serialNumber;

    @XmlElement(name = "take-off")
    @NotNull
    private String takeOff;
    @NotNull
    private PassengerDTO passenger;
    @NotNull
    private PlaneDTO plane;

    @XmlElement(name = "from-town")
    @NotNull
    private TownDTO fromTown;

    @XmlElement(name = "to-town")
    @NotNull
    private Town toTown;

    public ImportTicketDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public PassengerDTO getPassenger() {
        return passenger;
    }

    public PlaneDTO getPlane() {
        return plane;
    }

    public TownDTO getFromTown() {
        return fromTown;
    }

    public Town getToTown() {
        return toTown;
    }

    @Override
    public String toString() {
        return "ImportTicketDto{" +
                "price=" + price +
                ", serialNumber='" + serialNumber + '\'' +
                ", takeOff='" + takeOff + '\'' +
                ", passenger=" + passenger +
                ", plane=" + plane +
                ", fromTown=" + fromTown +
                ", toTown=" + toTown +
                '}';
    }
}
