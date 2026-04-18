package validators;

import data.DataService;
import domain.Student;
import domain.Teacher;
import exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.StudentRepository;
import repositories.TeacherRepository;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

public class PersonValidator {

    private static TeacherRepository repositoryT = TeacherRepository.getInstance();
    private static StudentRepository repositoryS = StudentRepository.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(PersonValidator.class);

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Teacher> maybeTeacher = repositoryT.findById(id);
        if(maybeTeacher.isPresent()){
            throw new DuplicateIdException("Помилка! Уже існує викладач з унікальним ідентифікатором " + id + ".");
        }else{
            Optional<Student> maybeStudent = repositoryS.findById(id);
            if(maybeStudent.isPresent()){
                throw new DuplicateIdException("Помилка! Уже існує студент з унікальним ідентифікатором " + id + ".");
            }
        }
    }

    public static boolean isBirthDateValid(int year, int month, int day){
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
