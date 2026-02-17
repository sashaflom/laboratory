package repositories;

import domain.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository {

    void addFaculty(Faculty faculty);
    void deleteFaculty(Faculty faculty);
    Optional<Faculty> findFacultyByUniqueCode(String uniqueCode);
    Optional<Faculty> findFacultyByFullName(String fullName);
    Optional<Faculty> findFacultyByShortName(String shortName);
    Faculty findLastAddedFaculty();
    List<Faculty> getFaculties();
}
