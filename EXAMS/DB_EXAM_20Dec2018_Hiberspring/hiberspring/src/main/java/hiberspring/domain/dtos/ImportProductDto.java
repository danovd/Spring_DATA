package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductDto {
@XmlAttribute
@NotNull
private String name;
@XmlAttribute
@NotNull
@Positive
private int clients;

@XmlElement
@NotNull
private String branch;

    public ImportProductDto() {
    }

    public String getName() {
        return name;
    }

    public int getClients() {
        return clients;
    }

    public String getBranch() {
        return branch;
    }
}
