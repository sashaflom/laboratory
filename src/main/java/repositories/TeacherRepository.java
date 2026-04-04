package repositories;

import domain.Faculty;
import domain.Teacher;

import java.util.*;
import java.util.function.Predicate;

public final class TeacherRepository implements Repository<Teacher, String> {

    // static class variable that will store a reference to a single instance of the class
    private static TeacherRepository instance = null;

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private TeacherRepository(){}

    // a static class method that should return a reference to a single instance of the class
    public static TeacherRepository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new TeacherRepository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    private Map<String, Teacher> teachers = new LinkedHashMap<>();
    private Teacher lastAdded;

    @Override
    public void add(Teacher teacher){
        teachers.put(teacher.getId(), teacher);
        lastAdded = teacher;
    }

    @Override
    public void delete(Teacher teacher){
        teachers.remove(teacher.getId());
    }

    @Override
    public Optional<Teacher> findById(String id){
        Optional<Teacher> foundTeacher = Optional.ofNullable(teachers.get(id));
        return foundTeacher;
    }

    public Teacher findLastAdded(){
        return lastAdded;
    }

    public List<Teacher> findAll(Predicate<Teacher> rule){
        return teachers.values().stream().filter(rule).toList() ;
    }


    @Override
    public List<Teacher> getAll() {
        return new ArrayList<>(teachers.values());
    }

    @Override
    public Map<String, Teacher> getMap(){
        return Map.copyOf(teachers);
    }

    public boolean teachersIsNotEmpty(){
        return teachers.size()!=0;
    }

}
