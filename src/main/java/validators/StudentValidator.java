package validators;

import domain.Student;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class StudentValidator extends PersonValidator{

    private static StudentRepository repository = StudentRepository.getInstance();

    public static boolean isIdValid(String id){
        Optional<Student> maybeStudent = repository.findById(id);
        if(maybeStudent.isEmpty()) return true;
        return false;
    }

    public static boolean isStudentIdValid(String id) {
        Predicate<Student> rule = student -> student.getStudentId().equals(id);
        List<Student> foundStudent = repository.findAll(rule);
        if(foundStudent.isEmpty()) return true;
        return false;
    }

}
