package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTaskDTO {



    private String date;



    @Positive
    private double price;

    @XmlElement(name = "car")
    private IdDTO car;
    @XmlElement(name = "mechanic")
    private MechanicDTO mechanic;
    @XmlElement(name = "part")
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
