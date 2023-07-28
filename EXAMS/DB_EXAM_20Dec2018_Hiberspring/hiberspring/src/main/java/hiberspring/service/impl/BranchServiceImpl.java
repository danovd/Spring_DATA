package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.repository.BranchRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class BranchServiceImpl implements BranchService {
    private static final String BRANCHES_FILE_PATH = "src/main/resources/files/branches.json";
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;

    private final FileUtil fileUtil;
    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, FileUtil fileUtil) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return null;
    }

    @Override
    public String importBranches(String branchesFileContent) {
        return null;
    }
}
