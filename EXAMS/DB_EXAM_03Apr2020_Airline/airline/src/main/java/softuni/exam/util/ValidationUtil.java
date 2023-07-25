package softuni.exam.util;

import javax.validation.ConstraintViolation;

public interface ValidationUtil {

    <E> boolean isValid(E entity);
}
