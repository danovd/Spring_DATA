package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.ImportTeamDto;
import softuni.exam.domain.dto.ImportTeamRootDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String TEAMS_FILE_PATH = "src/main/resources/files/xml/teams.xml";
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validator;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    private final PictureRepository pictureRepository;
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, ValidatorUtil validator, XmlParser xmlParser, FileUtil fileUtil, PictureRepository pictureRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public String importTeams() throws JAXBException {
        ImportTeamRootDTO planeRootDTOs = this.xmlParser.fromFile(TEAMS_FILE_PATH, ImportTeamRootDTO.class);
        return planeRootDTOs.getTeams().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportTeamDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid team";
        }

        Optional<Team> optTeam = this.teamRepository.findByName(dto.getName());


        if (optTeam.isPresent()) {
            return "Invalid team";
        }


        Team team = this.modelMapper.map(dto, Team.class);

        //// SET PICTURE
        Optional<Picture> picture = this.pictureRepository.findByUrl(dto.getPicture().getUrl());
        if(picture.isEmpty()){
            return "Invalid team";
        }
        team.setPicture(picture.get());
        ///

        this.teamRepository.save(team);

        return"Successfully imported picture " + team.getName();
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return fileUtil.readFile(TEAMS_FILE_PATH);
    }
}
