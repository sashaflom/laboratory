package validators;

import domain.Student;
import repositories.StudentRepository;

import java.util.Optional;

public class StudentValidator extends PersonValidator{

    private static StudentRepository repository = StudentRepository.getInstance();

    public static boolean isIdValid(String id){
        Optional<Student> maybeStudent = repository.findById(id);
        if(maybeStudent.isEmpty()) return true;
        return false;
    }

    public static boolean isStudentIdValid(String id) {
        Optional<Student> maybeStudent = repository.findByStudentId(id);
        if(maybeStudent.isEmpty()) return true;
        return false;
    }

}
