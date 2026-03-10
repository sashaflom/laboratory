package repositories;

import java.util.*;

public interface Repository<T, ID> {
    void add (T entity);
    void delete (T entity);
    Optional<T> findById (ID id);
    List<T> getAll();
}
