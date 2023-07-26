package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTaskDTO {


    @NotNull
    private String date;


    @NotNull
    @Positive
    private double price;

    @XmlElement(name = "car")
    @NotNull
    private IdDTO car;
    @XmlElement(name = "mechanic")
    @NotNull
    private MechanicDTO mechanic;
    @XmlElement(name = "part")
    @NotNull
    private IdDTO part;


    public ImportTaskDTO() {
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public IdDTO getCar() {
        return car;
    }

    public MechanicDTO getMechanic() {
        return mechanic;
    }

    public IdDTO getPart() {
        return part;
    }


    @Override
    public String toString() {
        return "ImportTaskDTO{" +
                "date='" + date + '\'' +
                ", price=" + price +
                ", car=" + car +
                ", mechanic=" + mechanic +
                ", part=" + part +
                '}';
    }
}
