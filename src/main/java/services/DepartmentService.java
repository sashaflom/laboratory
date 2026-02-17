package services;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import repositories.DepartmentRepository;
import repositories.FacultyRepository;

import java.util.List;
import java.util.Optional;

public class DepartmentService {

    private DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository){
        this.repository = repository;
    }

    public Department createNewAndAdd(String uniqueCode, String name, Faculty faculty, Teacher headOfDepartment, String location){
        Department department = new Department(uniqueCode, name, faculty, headOfDepartment, location);
        repository.addDepartment(department);
        return department;
    }

    public void delete(Department department){
        repository.deleteDepartment(department);
    }

    public Optional<Department> findByUniqueCode(String uniqueCode){
        return repository.findDepartmentByUniqueCode(uniqueCode);
    }

    public Department findLastAdded(){
        return repository.findLastAddedDepartment();
    }

    public List<Department> getAll(){
        return repository.getDepartments();
    }
}
