package services;

import domain.Faculty;
import domain.Teacher;
import repositories.FacultyRepository;

import java.util.List;
import java.util.Optional;

public class FacultyService {

    private FacultyRepository repository;

    public FacultyService(FacultyRepository repository){
        this.repository = repository;
    }

    public Faculty createNewAndAdd(String uniqueCode, String fullName, String shortName, Teacher dean, String contacts){
        Faculty faculty = new Faculty(uniqueCode, fullName, shortName, dean, contacts);
        repository.addFaculty(faculty);
        return faculty;
    }

    public void delete(Faculty faculty){
        repository.deleteFaculty(faculty);
    }

    public Optional<Faculty> findByUniqueCode(String uniqueCode){
        return repository.findFacultyByUniqueCode(uniqueCode);
    }

    public Faculty findLastAdded(){
        return repository.findLastAddedFaculty();
    }

    public List<Faculty> getAll(){
        return repository.getFaculties();
    }

}
