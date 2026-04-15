package services;

import domain.*;
import repositories.FacultyRepository;

import java.util.*;

public class FacultyService {

    private static FacultyRepository repository = FacultyRepository.getInstance();

    public static Faculty createNewAndAdd(String uniqueCode, String fullName, String shortName, Teacher dean, String contacts){
        Faculty faculty = new Faculty(uniqueCode, fullName, shortName, dean, contacts);
        repository.add(faculty);
        return faculty;
    }

    public static void delete(Faculty faculty){
        repository.delete(faculty);
        List<Department> departments = DepartmentService.findAllByFaculty(faculty);
        if (!departments.isEmpty()){
            for (Department dep : departments) {
                dep.setFaculty(null);
                List<Student> students = StudentService.findAllByDepartment(dep);
                if (!students.isEmpty()){
                    for (Student student : students){
                        student.setFaculty(null);
                    }
                }
                List<Teacher> teachers = TeacherService.findAllByDepartment(dep);
                if (!teachers.isEmpty()){
                    for (Teacher teacher : teachers){
                        teacher.setFaculty(null);
                    }
                }
            }
        }
    }

    public static Optional<Faculty> findById(String id){
        return repository.findById(id);
    }

    public static Faculty findLastAdded(){
        return repository.findLastAdded();
    }

    public static List<Faculty> getAll(){
        return repository.getAll();
    }

}
