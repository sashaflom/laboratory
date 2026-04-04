package validators;

import domain.*;
import exceptions.*;
import repositories.*;
import java.util.*;
import java.util.function.Predicate;

public class StudentValidator extends PersonValidator{

    private static StudentRepository repository = StudentRepository.getInstance();

    public static void isStudentIdValid(String id) throws DuplicateStudentIdException {
        Predicate<Student> rule = student -> student.getStudentId().equals(id);
        List<Student> foundStudent = repository.findAll(rule);
        if(!foundStudent.isEmpty()){
            throw new DuplicateStudentIdException("Помилка! Студент з номером залікової книжки " + id + " уже існує.");
        }
    }

}
