package validators;

import domain.*;
import exceptions.*;
import repositories.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class TeacherValidator extends PersonValidator{

    private static TeacherRepository repository = TeacherRepository.getInstance();

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Teacher> maybeTeacher = repository.findById(id);
        if(maybeTeacher.isPresent()){
            throw new DuplicateIdException("Помилка! Викладач з унікальним ідентифікатором " + id + " уже існує.");
        }
    }

    public static boolean isHireDateValid(int year, int month, int day){
        try{
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e){
            return false;
        }
    }

}
