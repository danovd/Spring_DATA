package softuni.exam.service;




public interface TownService {

    boolean areImported();

    String readTownsFileContent() ;
	
	String importTowns() ;
}
