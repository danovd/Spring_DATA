package com.example.football.service.impl;

import com.example.football.models.dto.ImportStatDto;
import com.example.football.models.dto.ImportStatRootDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.FileUtil;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatServiceImpl implements StatService {

    private static final String STATS_FILE_PATH = "src/main/resources/files/xml/stats.xml";
    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validator, FileUtil fileUtil) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return fileUtil.readFile(STATS_FILE_PATH);
    }

    @Override
    public String importStats() throws JAXBException {
        ImportStatRootDTO statRootDTOs = this.xmlParser.parseXml(STATS_FILE_PATH, ImportStatRootDTO.class);
        return statRootDTOs.getStats().stream().map(this::importDTO).collect(Collectors.joining("\n"));
    }

    private String importDTO(ImportStatDto dto) {
        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return "Invalid Stat";
        }

        Optional<Stat> optStat = this.statRepository.findByPassingAndShootingAndEndurance(
                dto.getPassing(), dto.getShooting(), dto.getEndurance());


        if (optStat.isPresent()) {
            return "Invalid Stat";
        }


        Stat stat = this.modelMapper.map(dto, Stat.class);


        this.statRepository.save(stat);

        // Обърканото е в примера: вместо passing-shooting-endurance са показали данните shоoting-passing-endurance!
        // Виж самия xml файл накрая как са данните!

        return"Successfully imported Stat " + stat.getPassing() + " - " + stat.getShooting() + " - " + stat.getEndurance();

    }
}
