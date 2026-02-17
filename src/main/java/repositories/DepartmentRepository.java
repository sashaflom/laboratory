package repositories;

import domain.Department;
import domain.Faculty;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    void addDepartment(Department department);
    void deleteDepartment(Department department);
    Optional<Department> findDepartmentByUniqueCode(String uniqueCode);
    Optional<Department> findDepartmentByName(String name);
    Department findLastAddedDepartment();
    List<Department> getDepartments();
}
