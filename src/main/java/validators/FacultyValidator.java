package validators;

import domain.Faculty;
import repositories.FacultyRepository;
import services.FacultyService;

import java.util.Optional;

public class FacultyValidator {

    private FacultyRepository repository;

    public FacultyValidator(FacultyRepository repository){
        this.repository = repository;
    }

    public boolean isUniqueCodeValid(String uniqueCode){
        Optional<Faculty> maybeFaculty = repository.findFacultyByUniqueCode(uniqueCode);
        if(maybeFaculty.isEmpty()) return true;
        return false;
    }

    public boolean isFullNameValid(String fullName){
        Optional<Faculty> maybeFaculty = repository.findFacultyByFullName(fullName);
        if(maybeFaculty.isEmpty()) return true;
        return false;
    }

    public boolean isShortNameValid(String shortName){
        Optional<Faculty> maybeFaculty = repository.findFacultyByShortName(shortName);
        if(maybeFaculty.isEmpty()) return true;
        return false;
    }

}
