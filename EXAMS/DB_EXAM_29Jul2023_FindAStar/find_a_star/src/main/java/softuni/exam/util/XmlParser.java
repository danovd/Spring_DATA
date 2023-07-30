package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T parseXml(String filePath, Class<T> objectClass) throws JAXBException;
}
