package validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.*;

import java.time.DateTimeException;
import java.time.LocalDate;

public class TeacherValidator extends PersonValidator{

    private static TeacherRepository repository = TeacherRepository.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TeacherValidator.class);

    public static boolean isHireDateValid(int year, int month, int day){
        try{
            LocalDate.of(year, month, day);
            logger.info("Дата {} валідна", LocalDate.of(year, month, day));
            return true;
        } catch (DateTimeException e){
            logger.error("Помилка, невалідна дата для чисел рік {} місяць {} день {}: {}", year, month, day, e.getMessage());
            return false;
        }
    }

}
