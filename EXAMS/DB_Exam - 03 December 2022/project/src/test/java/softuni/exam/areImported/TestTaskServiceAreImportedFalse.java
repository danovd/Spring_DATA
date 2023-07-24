package softuni.exam.areImported;
//TestTasksServiceAreImportedFalse

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.impl.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestTaskServiceAreImportedFalse {

    @InjectMocks
    private TaskServiceImpl tasksService;
    @Mock
    private TaskRepository mockTaskRepository;

    @Test
    void areImportedShouldReturnFalse() {
        Mockito.when(mockTaskRepository.count()).thenReturn(0L);
        Assertions.assertFalse(tasksService.areImported());
    }
}