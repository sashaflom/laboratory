package services;

import domain.*;
import repositories.TeacherRepository;

import java.util.*;
import java.time.LocalDate;

public class TeacherService {

    private static TeacherRepository repository = TeacherRepository.getInstance();

    public static Teacher createNewAndAdd(String id, String lastName, String firstName, String patronymic, LocalDate birthDate,
                                   String email, String phoneNumber, Department department, Position position,
                                   AcademicDegree academicDegree, AcademicTitle academicTitle, LocalDate hireDate, double workload){
        Teacher teacher = new Teacher(id, lastName, firstName, patronymic, birthDate, email, phoneNumber, department, position,
                academicDegree, academicTitle, hireDate, workload);
        repository.add(teacher);
        return teacher;
    }

    public static void delete(Teacher teacher){
        repository.delete(teacher);
    }

    public static Optional<Teacher> findById(String id){
        return repository.findById(id);
    }

    public static Teacher findLastAdded(){
        return repository.findLastAdded();
    }

    public static List<Teacher> findByFullName(String fullName){
        return repository.findAll(t -> t.getFullName().equals(fullName));
    }
    public static List<Teacher> getAllTeachersSortedByAlphabet(){
        List<Teacher> all = new ArrayList<>(repository.findAll(t ->true));
        all.sort((t1, t2) -> t1.getFullName().compareTo(t2.getFullName()));
        return all;
    }
    public static List<Teacher> getAllTeachersSortedByDepartment(Department department){
        List<Teacher> teachersList = new ArrayList<>(repository.findAll (teacher ->teacher.getDepartment().equals(department)));
        teachersList.sort((t1,t2)-> t1.getFullName().compareTo(t2.getFullName()));
        return teachersList;
    }
    public static List<Teacher> getAllTeachersSortedByFaculty(Faculty faculty){
        List<Teacher> teachersList1 = new ArrayList<>(repository.findAll (teacher1 ->teacher1.getFaculty().equals(faculty)));
        teachersList1.sort((t1, t2) -> t1.getFullName().compareTo(t2.getFullName()));
        return teachersList1;
    }

    public static List<Teacher> getAll(){
        return repository.getAll();
    }

    public static boolean isThereSomethingInRepository(){
        return repository.teachersIsNotEmpty();
    }

}
