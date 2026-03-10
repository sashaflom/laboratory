package validators;

import domain.Teacher;
import exceptions.DuplicateIdException;
import repositories.TeacherRepository;

import java.util.Optional;

public class TeacherValidator extends PersonValidator{

    private static TeacherRepository repository = TeacherRepository.getInstance();

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Teacher> maybeTeacher = repository.findById(id);
        if(maybeTeacher.isPresent()){
            throw new DuplicateIdException("Помилка! Викладач з унікальним ідентифікатором " + id + " уже існує.");
        }
    }

}
