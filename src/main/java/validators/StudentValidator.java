package validators;

import domain.Student;
import domain.Teacher;
import repositories.StudentRepository;
import repositories.TeacherRepository;

import java.util.Optional;

public class StudentValidator extends PersonValidator{

    private StudentRepository repository;

    public StudentValidator(StudentRepository repository){
        this.repository = repository;
    }

    public boolean isIdValid(String id){
        Optional<Student> maybeStudent = repository.findStudentById(id);
        if(maybeStudent.isEmpty()) return true;
        return false;
    }

    public boolean isStudentIdValid(String id) {
        Optional<Student> maybeStudent = repository.findStudentByStudentId(id);
        if(maybeStudent.isEmpty()) return true;
        return false;
    }

}
