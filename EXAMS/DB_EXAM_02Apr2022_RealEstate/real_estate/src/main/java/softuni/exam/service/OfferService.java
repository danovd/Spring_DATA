package softuni.exam.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface OfferService {

    boolean areImported();

    String readOffersFileContent() throws IOException;
	
	String importOffers() throws IOException, JAXBException;

    String exportOffers();
}
