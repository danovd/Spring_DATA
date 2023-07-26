package softuni.exam.service;





import java.io.IOException;


public interface CarService {

    boolean areImported() throws IOException;

    String readCarsFileContent() throws IOException;
	
	String importCars() throws IOException;

    String getCarsOrderByPicturesCountThenByMake();
}
