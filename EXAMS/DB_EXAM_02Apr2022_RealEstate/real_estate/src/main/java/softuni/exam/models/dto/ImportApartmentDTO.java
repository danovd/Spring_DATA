package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportApartmentDTO {
    @NotNull
    private ApartmentType apartmentType;

    @NotNull
    @DecimalMin("40")
    private double area;


    private String town;


    public ImportApartmentDTO() {
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public double getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
}
