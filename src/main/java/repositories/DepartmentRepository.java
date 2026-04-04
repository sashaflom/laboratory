package repositories;

import domain.Department;
import domain.Faculty;

import java.util.*;

public final class DepartmentRepository implements Repository<Department, String> {

    // static class variable that will store a reference to a single instance of the class
    private static DepartmentRepository instance = null;

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private DepartmentRepository(){}

    // a static class method that should return a reference to a single instance of the class
    public static DepartmentRepository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new DepartmentRepository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    private Map<String, Department> departments = new LinkedHashMap<>();
    private Department lastAdded;

    @Override
    public void add(Department department) {
        departments.put(department.getId(), department);
        lastAdded = department;
    }

    @Override
    public void delete(Department department) {
        departments.remove(department.getId());
    }

    @Override
    public Optional<Department> findById(String id){
        Optional<Department> foundDepartament = Optional.ofNullable(departments.get(id));
        return foundDepartament;
    }

    public Optional<Department> findByName(String name){
        Optional<Department> foundDepartament = Optional.empty();
        if (departments.size()!=0) {
            for (Department department : departments.values()) {
                if (department.getName().equals(name)) {
                    foundDepartament = Optional.of(department);
                    break;
                }
            }
        }
        return foundDepartament;
    }

    public Department findLastAdded(){
        return lastAdded;
    }

    @Override
    public List<Department> getAll() {
        return new ArrayList<>(departments.values());
    }

    @Override
    public Map<String, Department> getMap(){
        return Map.copyOf(departments);
    }
}
