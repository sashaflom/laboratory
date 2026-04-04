package repositories;

import domain.Faculty;
import domain.Teacher;

import java.util.*;

public final class FacultyRepository implements Repository<Faculty, String> {

    // static class variable that will store a reference to a single instance of the class
    private static FacultyRepository instance = null;

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private FacultyRepository(){}

    // a static class method that should return a reference to a single instance of the class
    public static FacultyRepository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new FacultyRepository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    private Map<String, Faculty> faculties = new LinkedHashMap<>();
    private Faculty lastAdded;

    @Override
    public void add(Faculty faculty){
        faculties.put(faculty.getId(), faculty);
        lastAdded = faculty;
    }

    @Override
    public void delete(Faculty faculty){
        faculties.remove(faculty.getId());
    }

    @Override
    public Optional<Faculty> findById(String id){
        Optional<Faculty> foundFaculty = Optional.ofNullable(faculties.get(id));
        return foundFaculty;
    }

    public Optional<Faculty> findByFullName(String fullName){
        Optional<Faculty> foundFaculty = Optional.empty();
        if(faculties.size()!=0){
            for(Faculty faculty : faculties.values()){
                if (faculty.getFullName().equals(fullName)){
                    foundFaculty = Optional.of(faculty);
                    break;
                }
            }
        }
        return foundFaculty;
    }

    public Optional<Faculty> findByShortName(String shortName){
        Optional<Faculty> foundFaculty = Optional.empty();
        if(faculties.size()!=0){
            for(Faculty faculty : faculties.values()){
                if (faculty.getShortName().equals(shortName)){
                    foundFaculty = Optional.of(faculty);
                    break;
                }
            }
        }
        return foundFaculty;
    }

    public Faculty findLastAdded(){
        return lastAdded;
    }

    @Override
    public List<Faculty> getAll() {
        return new ArrayList<>(faculties.values());
    }

    @Override
    public Map<String, Faculty> getMap(){
        return Map.copyOf(faculties);
    }

    @Override
    public void setMap(Map<String, Faculty> map){
        faculties = new LinkedHashMap<>(map);
    }

}
