package services;

import domain.*;
import repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;
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
        return repository.findByFullName(fullName);
    }

    public static List<Teacher> getAll(){
        return repository.getAll();
    }

    public static boolean isThereSomethingInRepository(){
        return repository.teachersIsNotEmpty();
    }

}
