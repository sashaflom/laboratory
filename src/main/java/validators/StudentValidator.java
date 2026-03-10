package validators;

import domain.*;
import exceptions.*;
import repositories.*;
import java.util.*;
import java.util.function.Predicate;

public class StudentValidator extends PersonValidator{

    private static StudentRepository repository = StudentRepository.getInstance();

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Student> maybeStudent = repository.findById(id);
        if(maybeStudent.isPresent()){
            throw new DuplicateIdException("Помилка! Студент з унікальним ідентифікатором " + id + " уже існує.");
        }
    }

    public static void isStudentIdValid(String id) throws DuplicateStudentIdException {
        Predicate<Student> rule = student -> student.getStudentId().equals(id);
        List<Student> foundStudent = repository.findAll(rule);
        if(!foundStudent.isEmpty()){
            throw new DuplicateStudentIdException("Помилка! Студент з номером залікової книжки " + id + " уже існує.");
        }
    }

}
