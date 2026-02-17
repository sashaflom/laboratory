package validators;

import domain.Teacher;
import repositories.FacultyRepository;
import repositories.TeacherRepository;

import java.util.Optional;

public class TeacherValidator extends PersonValidator{

    private TeacherRepository repository;

    public TeacherValidator(TeacherRepository repository){
        this.repository = repository;
    }

    public boolean isIdValid(String id){
        Optional<Teacher> maybeTeacher = repository.findTeacherById(id);
        if(maybeTeacher.isEmpty()) return true;
        return false;
    }

}
