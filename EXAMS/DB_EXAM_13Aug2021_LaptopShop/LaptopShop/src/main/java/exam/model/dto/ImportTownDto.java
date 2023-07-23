package exam.model.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTownDto {
    @Size(min = 2)
    private String name;

    @Positive
    private int population;
    @Size(min = 10)
    @XmlElement(name = "travel-guide")
    private String travelGuide;


    public ImportTownDto() {
    }


    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }
}
