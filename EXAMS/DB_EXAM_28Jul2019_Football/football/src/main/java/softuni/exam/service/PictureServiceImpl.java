package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.XmlParser;

import javax.validation.Validator;
import java.io.IOException;


@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURES_FILE_PATH = "src/main/resources/files/xml/pictures.xml";

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Validator validator, XmlParser xmlParser, FileUtil fileUtil) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
    }


    @Override
    public String importPictures() {
        //TODO Implement me
        return "";
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return fileUtil.readFile(PICTURES_FILE_PATH);
    }

}
