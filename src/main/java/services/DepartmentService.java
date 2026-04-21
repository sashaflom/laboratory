package services;

import domain.*;
import repositories.DepartmentRepository;

import java.util.*;
import java.util.function.Predicate;

public class DepartmentService {

    private static DepartmentRepository repository = DepartmentRepository.getInstance();

    public static Department createNewAndAdd(String uniqueCode, String name, Faculty faculty, Teacher headOfDepartment, String location){
        Department department = new Department(uniqueCode, name, faculty, headOfDepartment, location);
        repository.add(department);
        return department;
    }

    public static void delete(Department department){
        repository.delete(department);
        if (department.getHeadOfDepartment() != null){
            department.getHeadOfDepartment().setPosition(Position.TEACHER);
        }
        List<Student> students = StudentService.findAllByDepartment(department);
        if (!students.isEmpty()){
            for (Student student : students){
                student.setDepartment(null);
                student.setFaculty(null);
            }
        }
        List<Teacher> teachers = TeacherService.findAllByDepartment(department);
        if (!teachers.isEmpty()){
            for (Teacher teacher : teachers){
                teacher.setDepartment(null);
                teacher.setFaculty(null);
            }
        }
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

    public static List<Department> findAllByFaculty(Faculty faculty){
        Predicate<Department> rule = department -> department.getFaculty() != null && department.getFaculty().equals(faculty);
        return repository.findAll(rule);
    }

    public static void deleteFaculty (Faculty faculty){

    }
}
