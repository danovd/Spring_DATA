package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchImportDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;



@Service
public class BranchServiceImpl implements BranchService {
    private static final String BRANCHES_FILE_PATH = PATH_TO_FILES + "branches.json";
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final FileUtil fileUtil;

    private final TownRepository townRepository;
    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return fileUtil.readFile(BRANCHES_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        String json = this.readBranchesJsonFile();

        BranchImportDTO[] importDTOs = this.gson.fromJson(json, BranchImportDTO[].class);

        return Arrays.stream(importDTOs)
                .map(this::importDTO)
                .collect(Collectors.joining("\n"));
    }

    private String importDTO(BranchImportDTO dto) {

        boolean isValid = this.validator.isValid(dto);

        if (!isValid) {
            return INCORRECT_DATA_MESSAGE;
        }

        Optional<Branch> optBranch = this.branchRepository.findByNameAndTownName(dto.getName(), dto.getTown());


        if (optBranch.isPresent()) {
            return INCORRECT_DATA_MESSAGE;
        }

        Branch branch = this.modelMapper.map(dto, Branch.class);

        //// SET Town
        Town town = townRepository.getTownByName(dto.getTown());
        branch.setTown(town);
        /////////////////////////

        this.branchRepository.save(branch);

        return String.format(SUCCESSFUL_IMPORT_MESSAGE, "Branch", branch.getName());
    }
}
