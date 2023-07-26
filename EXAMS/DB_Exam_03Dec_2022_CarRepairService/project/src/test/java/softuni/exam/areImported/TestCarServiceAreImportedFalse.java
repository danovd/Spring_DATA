package softuni.exam.areImported;
//TestCarsServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.impl.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestCarServiceAreImportedFalse {

    @InjectMocks
    private CarServiceImpl carsService;
    @Mock
    private CarRepository carRepository;


    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(carRepository.count()).thenReturn(0L);
        Assertions.assertFalse(carsService.areImported());
    }
}