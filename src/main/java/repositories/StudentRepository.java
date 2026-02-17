package repositories;

import domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void addStudent(Student student);
    void deleteStudent(Student student);
    Optional<Student> findStudentById(String id);
    Optional<Student> findStudentByStudentId(String id);
    List<Student> findStudentsByFullName(String fullName);
    List<Student> findStudentsByCourse(int course);
    List<Student> findStudentsByGroup(String group);
    List<Student> getStudents();
    boolean studentsIsNotEmpty();

}
