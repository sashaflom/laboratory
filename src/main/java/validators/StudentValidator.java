package validators;

import domain.Student;
import exceptions.DuplicateIdException;
import exceptions.DuplicateStudentIdException;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
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
