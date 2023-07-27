package softuni.exam.util;

import org.springframework.stereotype.Component;

/*import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;*/

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;


public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings("unchecked")
    public <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new File(filePath));
    }
}
