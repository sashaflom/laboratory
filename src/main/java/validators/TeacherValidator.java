package validators;

import repositories.*;

import java.time.DateTimeException;
import java.time.LocalDate;

public class TeacherValidator extends PersonValidator{

    private static TeacherRepository repository = TeacherRepository.getInstance();

    public static boolean isHireDateValid(int year, int month, int day){
        try{
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e){
            return false;
        }
    }

}
