package validators;

import domain.Department;
import repositories.DepartmentRepository;

import java.util.Optional;

public class DepartmentValidator {

    private static DepartmentRepository repository = DepartmentRepository.getInstance();

    public static boolean isIdValid(String id){
        Optional<Department> maybeDepartment = repository.findById(id);
        if(maybeDepartment.isEmpty()) return true;
        return false;
    }

    public static boolean isNameValid(String name){
        Optional<Department> maybeDepartment = repository.findByName(name);
        if(maybeDepartment.isEmpty()) return true;
        return false;
    }

}
