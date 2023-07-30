package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;


public class AstronomerServiceImpl implements AstronomerService {


    private static final String ASTRONOMERS_FILE_PATH = "src/main/resources/files/xml/astronomers.xml";
    private final AstronomerRepository astronomerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;
    private final StarRepository starRepository;


    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil, StarRepository starRepository) {
        this.astronomerRepository = astronomerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.starRepository = starRepository;
    }
    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return null;
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        return null;
    }
}
