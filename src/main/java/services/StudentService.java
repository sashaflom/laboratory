package services;

import domain.*;
import repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }


    public Student createNewAndAdd(String id, String lastName, String firstName, String patronymic, String birthDate,
                                   String email, String phoneNumber, Department department, String studentId, int course,
                                   String group, int enrollmentYear, EducationForm educationForm, StudentStatus status){
        Student student = new Student(id, lastName, firstName, patronymic, birthDate, email, phoneNumber, department, studentId,
                course, group, enrollmentYear, educationForm, status);
        repository.addStudent(student);
        return student;
    }

    public void delete(Student student){
        repository.deleteStudent(student);
    }

    public Optional<Student> findById(String id){
        return repository.findStudentById(id);
    }

    public List<Student> findByFullName(String fullName){
        return repository.findStudentsByFullName(fullName);
    }

    public List<Student> findByCourse(int course){
        return repository.findStudentsByCourse(course);
    }

    public List<Student> findByGroup(String group){
        return repository.findStudentsByGroup(group);
    }

    public List<Student> getAll(){
        return repository.getStudents();
    }

    public boolean isThereSomethingInRepository(){
        return repository.studentsIsNotEmpty();
    }
}
