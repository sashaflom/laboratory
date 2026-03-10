package validators;

import domain.Faculty;
import exceptions.DuplicateNameException;
import exceptions.DuplicateIdException;
import repositories.FacultyRepository;

import java.util.Optional;

public class FacultyValidator {

    private static FacultyRepository repository = FacultyRepository.getInstance();

    public static void isIdValid(String id) throws DuplicateIdException {
        Optional<Faculty> maybeFaculty = repository.findById(id);
        if(maybeFaculty.isPresent()){
            throw new DuplicateIdException("Помилка! Факультет з унікальним ідентифікатором " + id + " уже існує.");
        }
    }

    public static void isFullNameValid(String fullName) throws DuplicateNameException {
        Optional<Faculty> maybeFaculty = repository.findByFullName(fullName);
        if(maybeFaculty.isPresent()){
            throw new DuplicateNameException("Помилка! Факультет з повною назвою " + fullName + " вже існує.");
        }
    }

    public static void isShortNameValid(String shortName) throws DuplicateNameException{
        Optional<Faculty> maybeFaculty = repository.findByShortName(shortName);
        if(maybeFaculty.isPresent()){
            throw new DuplicateNameException("Помилка! Факультет зі скороченою назвою " + shortName + " вже існує.");
        }
    }

}
