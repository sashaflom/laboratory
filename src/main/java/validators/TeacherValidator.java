package validators;

import domain.Teacher;
import repositories.TeacherRepository;

import java.util.Optional;

public class TeacherValidator extends PersonValidator{

    private static TeacherRepository repository = TeacherRepository.getInstance();

    public static boolean isIdValid(String id){
        Optional<Teacher> maybeTeacher = repository.findById(id);
        if(maybeTeacher.isEmpty()) return true;
        return false;
    }

}
