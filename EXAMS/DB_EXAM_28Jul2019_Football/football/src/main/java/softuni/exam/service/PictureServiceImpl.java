package softuni.exam.service;

//import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import softuni.exam.domain.dto.ImportPictureDto;
import softuni.exam.domain.dto.ImportPictureRootDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.ValidatorUtilImpl;
import softuni.exam.util.XmlParser;

import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PictureServiceImpl implements PictureService {

    private static final String PICTURES_FILE_PATH = "src/main/resources/files/xml/pictures.xml";

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validator;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, ValidatorUtil validator, XmlParser xmlParser, FileUtil fileUtil) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
    }


    @Override
    public String importPictures() throws JAXBException {
        ImportPictureRootDTO planeRootDTOs = this.xmlParser.fromFile(PICTURES_FILE_PATH, ImportPictureRootDTO.class);
        return planeRootDTOs.getPictures().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportPictureDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid picture";
        }

        Optional<Picture> optPicture = this.pictureRepository.findByUrl(dto.getUrl());


        if (optPicture.isPresent()) {
            return "Invalid picture";
        }


        Picture picture = this.modelMapper.map(dto, Picture.class);



        this.pictureRepository.save(picture);

        return"Successfully imported picture " + picture.getUrl();
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
