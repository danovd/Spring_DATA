package exam.service;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

//ToDo - Implement all methods
public interface ShopService {

    boolean areImported();

    String readShopsFileContent() throws IOException;

    String importShops() throws JAXBException, FileNotFoundException;
}
