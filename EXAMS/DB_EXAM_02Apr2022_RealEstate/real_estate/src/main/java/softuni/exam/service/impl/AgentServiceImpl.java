package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {


    private static final String AGENTS_FILE_PATH = "src/main/resources/files/json/agents.json";

    private final AgentRepository agentRepository;

    private final TownRepository townRepository;


    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {

        this.agentRepository = agentRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

    }


    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENTS_FILE_PATH));
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile();

        AgentImportDTO[] importDTOs = this.gson.fromJson(json, AgentImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(AgentImportDTO dto) {
        Set<ConstraintViolation<AgentImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid agent";
        }



        Optional<Town> townById = townRepository.getTownByTownName(dto.getTown());



        Optional<Agent> optAgent = this.agentRepository.findByFirstName(dto.getFirstName());




        if (optAgent.isPresent()) {
            return "Invalid agent";
        }

        Agent agent = this.modelMapper.map(dto, Agent.class);



        ///////////////////////////  Operation SET City of that Forecast  ////////////////
        agent.setTown(townById.get());
        /////////////////////////////////////////////////////////////////////////////////



        this.agentRepository.save(agent);

        return"Successfully imported agent " + agent.getFirstName() + " " + agent.getLastName();
    }
}
