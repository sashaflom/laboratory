package services;

import domain.*;
import repositories.FacultyRepository;
import repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;

public class TeacherService {

    private TeacherRepository repository;

    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }

    public Teacher createNewAndAdd(String id, String lastName, String firstName, String patronymic, String birthDate,
                                   String email, String phoneNumber, Department department, Position position,
                                   AcademicDegree academicDegree, AcademicTitle academicTitle, String hireDate, double workload){
        Teacher teacher = new Teacher(id, lastName, firstName, patronymic, birthDate, email, phoneNumber, department, position,
                academicDegree, academicTitle, hireDate, workload);
        repository.addTeacher(teacher);
        return teacher;
    }

    public void delete(Teacher teacher){
        repository.deleteTeacher(teacher);
    }

    public Optional<Teacher> findById(String id){
        return repository.findTeacherById(id);
    }

    public Teacher findLastAdded(){
        return repository.findLastAddedTeacher();
    }

    public List<Teacher> findByFullName(String fullName){
        return repository.findTeachersByFullName(fullName);
    }

    public List<Teacher> getAll(){
        return repository.getTeachers();
    }

    public boolean isThereSomethingInRepository(){
        return repository.teachersIsNotEmpty();
    }

}
