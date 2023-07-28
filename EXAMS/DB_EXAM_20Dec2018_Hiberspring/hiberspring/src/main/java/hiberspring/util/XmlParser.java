package hiberspring.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException;
}
