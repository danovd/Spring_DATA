package softuni.exam.models.dto;

import softuni.exam.models.entity.Rating;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSellerDto {

    @Size(min = 2, max = 19)
    @XmlElement(name = "first-name")
    private String firstName;
    @Size(min = 2, max = 19)
    @XmlElement(name = "last-name")
    private String lastName;
    @Email
  //  @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
//    @Enumerated(EnumType.STRING)
    private Rating rating;

    @NotNull
    private String town;

    public ImportSellerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Rating getRating() {
        return rating;
    }

    public String getTown() {
        return town;
    }
}
