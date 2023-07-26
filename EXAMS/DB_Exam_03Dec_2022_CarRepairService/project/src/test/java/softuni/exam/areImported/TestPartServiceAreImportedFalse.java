package softuni.exam.areImported;
//TestPartsServiceAreImportedFalse

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
public class TestPartServiceAreImportedFalse {

    @InjectMocks
    private PartServiceImpl partsService;
    @Mock
    private PartRepository mockPartRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockPartRepository.count()).thenReturn(0L);
        Assertions.assertFalse(partsService.areImported());
    }
}