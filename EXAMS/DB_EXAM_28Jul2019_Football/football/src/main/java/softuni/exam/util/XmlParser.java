package softuni.exam.util;

import jakarta.xml.bind.JAXBException;

//import javax.xml.bind.JAXBException;


import jakarta.xml.bind.JAXBException;
public interface XmlParser {
    <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, JAXBException;
}
