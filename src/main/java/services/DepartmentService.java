package services;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;

public class DepartmentService {

    private static DepartmentRepository repository = DepartmentRepository.getInstance();

    public static Department createNewAndAdd(String uniqueCode, String name, Faculty faculty, Teacher headOfDepartment, String location){
        Department department = new Department(uniqueCode, name, faculty, headOfDepartment, location);
        repository.add(department);
        return department;
    }

    public static void delete(Department department){
        repository.delete(department);
    }

    public static Optional<Department> findById(String id){
        return repository.findById(id);
    }

    public static Department findLastAdded(){
        return repository.findLastAdded();
    }

    public static List<Department> getAll(){
        return repository.getAll();
    }
}
