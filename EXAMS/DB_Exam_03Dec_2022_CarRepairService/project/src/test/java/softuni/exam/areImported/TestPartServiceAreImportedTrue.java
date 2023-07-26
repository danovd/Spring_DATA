package softuni.exam.areImported;
//TestPartsServiceAreImportedTrue

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.impl.PartServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestPartServiceAreImportedTrue {

    @InjectMocks
    private PartServiceImpl partsService;
    @Mock
    private PartRepository mockPartRepository;

    @Test
    void areImportedShouldReturnTrue() {
        Mockito.when(mockPartRepository.count()).thenReturn(1L);
        Assertions.assertTrue(partsService.areImported());
    }
}