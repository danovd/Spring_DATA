package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.XmlParser;

import javax.validation.Validator;

@Service
public class TeamServiceImpl implements TeamService {


    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, Validator validator, XmlParser xmlParser, FileUtil fileUtil) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
    }


    @Override
    public String importTeams() {
        //TODO Implement me
        return "";
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile(){
        //TODO Implement me
        return "";
    }
}
