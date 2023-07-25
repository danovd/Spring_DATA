package softuni.exam.service;






public interface PlaneService {

    boolean areImported();

    String readPlanesFileContent() ;
	
	String importPlanes();

}
