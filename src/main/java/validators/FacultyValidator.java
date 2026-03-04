package validators;

import domain.Faculty;
import repositories.FacultyRepository;

import java.util.Optional;

public class FacultyValidator {

    private static FacultyRepository repository = FacultyRepository.getInstance();

    public static boolean isIdValid(String id){
        Optional<Faculty> maybeFaculty = repository.findById(id);
        if(maybeFaculty.isEmpty()) return true;
        return false;
    }

    public static boolean isFullNameValid(String fullName){
        Optional<Faculty> maybeFaculty = repository.findByFullName(fullName);
        if(maybeFaculty.isEmpty()) return true;
        return false;
    }

    public static boolean isShortNameValid(String shortName){
        Optional<Faculty> maybeFaculty = repository.findByShortName(shortName);
        if(maybeFaculty.isEmpty()) return true;
        return false;
    }

}
