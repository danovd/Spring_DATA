package softuni.exam.models.dto;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class ImportAstronomerDto {
    @XmlElement(name = "first_name")
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;
    @XmlElement(name = "last_name")
    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;
    @XmlElement(name = "salary")
    @DecimalMin("15000.00")
    @NotNull
    private double salary;
    @XmlElement(name = "average_observation_hours")
    @DecimalMin("500.00")
    @NotNull
    private double averageObservationHours;
    @XmlElement(name = "birthday")
    private String birthday;
    @XmlElement(name = "observing_star_id")
    @NotNull
    private Long observingStar;


    public ImportAstronomerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public double getAverageObservationHours() {
        return averageObservationHours;
    }

    public String getBirthday() {
        return birthday;
    }

    public Long getObservingStar() {
        return observingStar;
    }
}
