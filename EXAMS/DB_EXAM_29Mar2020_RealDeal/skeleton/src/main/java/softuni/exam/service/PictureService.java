package softuni.exam.service;


import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface PictureService {

    boolean areImported();

    String readPicturesFromFile() throws IOException;
	
	String importPictures() throws IOException;

}
