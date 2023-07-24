package softuni.exam.areImported;
//TestMechanicsServiceAreImportedTrue

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.impl.MechanicServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestMechanicServiceAreImportedTrue {

    @InjectMocks
    private MechanicServiceImpl mechanicsService;
    @Mock
    private MechanicRepository mockMechanicRepository;

    @Test
    void areImportedShouldReturnTrue() {
        Mockito.when(mockMechanicRepository.count()).thenReturn(1L);
        Assertions.assertTrue(mechanicsService.areImported());
    }
}