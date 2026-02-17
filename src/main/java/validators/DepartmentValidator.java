package validators;

import domain.Department;
import domain.Faculty;
import repositories.DepartmentRepository;
import repositories.FacultyRepository;

import java.util.Optional;

public class DepartmentValidator {

    private DepartmentRepository repository;

    public DepartmentValidator(DepartmentRepository repository){
        this.repository = repository;
    }

    public boolean isUniqueCodeValid(String uniqueCode){
        Optional<Department> maybeDepartment = repository.findDepartmentByUniqueCode(uniqueCode);
        if(maybeDepartment.isEmpty()) return true;
        return false;
    }

    public boolean isNameValid(String name){
        Optional<Department> maybeDepartment = repository.findDepartmentByName(name);
        if(maybeDepartment.isEmpty()) return true;
        return false;
    }

}
