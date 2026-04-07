package repositories;

import java.util.*;

public sealed interface Repository<T, ID> permits DepartmentRepository, FacultyRepository, StudentRepository, TeacherRepository, UserRepository {
    void add (T entity);
    void delete (T entity);
    Optional<T> findById (ID id);
    List<T> getAll();
    Map<ID, T> getMap();
    void setMap(Map<ID,T> map);
}
