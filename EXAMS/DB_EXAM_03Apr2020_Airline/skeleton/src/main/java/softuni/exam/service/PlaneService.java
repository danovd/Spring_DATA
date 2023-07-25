package softuni.exam.service;





//ToDo - Before start App implement this Service and set areImported to return false
public interface PlaneService {

    boolean areImported();

    String readPlanesFileContent() ;
	
	String importPlanes();

}
