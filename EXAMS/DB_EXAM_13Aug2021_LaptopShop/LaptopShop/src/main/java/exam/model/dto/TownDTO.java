package exam.model.dto;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;







////////////    Ползва се като XML DTO при импорта на Shop, където се съдържа като вкложен елемент
////////////    Ползва се и като JSON DTO при импорта на Customer, където също е вложен елемент

@XmlAccessorType(XmlAccessType.FIELD)
public class TownDTO {

    private String name;

    public TownDTO() {
    }


    public String getName() {
        return name;
    }
}
