package repositories;

import domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    void addTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    Optional<Teacher> findTeacherById(String id);
    Teacher findLastAddedTeacher();
    List<Teacher> findTeachersByFullName(String fullName);
    List<Teacher> getTeachers();
    boolean teachersIsNotEmpty();
}
