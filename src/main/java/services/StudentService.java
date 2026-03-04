package services;

import domain.*;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private static StudentRepository repository = StudentRepository.getInstance();

    public static Student createNewAndAdd(String id, String lastName, String firstName, String patronymic, String birthDate,
                                   String email, String phoneNumber, Department department, String studentId, int course,
                                   String group, int enrollmentYear, EducationForm educationForm, StudentStatus status){
        Student student = new Student(id, lastName, firstName, patronymic, birthDate, email, phoneNumber, department, studentId,
                course, group, enrollmentYear, educationForm, status);
        repository.add(student);
        return student;
    }

    public static void delete(Student student){
        repository.delete(student);
    }

    public static Optional<Student> findById(String id){
        return repository.findById(id);
    }

    public static List<Student> findByFullName(String fullName){
        return repository.findByFullName(fullName);
    }

    public static List<Student> findByCourse(int course){
        return repository.findByCourse(course);
    }

    public static List<Student> findByGroup(String group){
        return repository.findByGroup(group);
    }

    public static List<Student> getAll(){
        return repository.getAll();
    }

    public static boolean isThereSomethingInRepository(){
        return repository.studentsIsNotEmpty();
    }
}
