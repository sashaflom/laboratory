package repositories;

import domain.Faculty;
import domain.Student;
import domain.Teacher;

import java.util.*;
import java.util.function.Predicate;

public final class StudentRepository implements Repository<Student, String> {

    // static class variable that will store a reference to a single instance of the class
    private static StudentRepository instance = null;

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private StudentRepository(){}

    // a static class method that should return a reference to a single instance of the class
    public static StudentRepository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new StudentRepository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    private Map<String, Student> students = new LinkedHashMap<>();

    @Override
    public void add(Student student){
        students.put(student.getId(), student);
    }

    @Override
    public void delete(Student student){
        students.remove(student.getId());
    }

    @Override
    public Optional<Student> findById(String id) {
        Optional<Student> foundStudent = Optional.ofNullable(students.get(id));
        return foundStudent;
    }

    public List<Student> findAll(Predicate<Student> rule) {
        List<Student> foundStudents = students.values().stream()
                .filter(rule)
                .toList();
        return foundStudents;
    }

    public List<Student> findPart(Predicate<Student> rule, List<Student> list) {
        List<Student> foundStudents = list.stream()
                .filter(rule)
                .toList();
        return foundStudents;
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(students.values());
    }

    public boolean studentsIsNotEmpty(){
        return students.size()!=0;
    }

    public List<Student> sortAll(Comparator<Student> rule){
        List<Student> sorted = students.values().stream()
                .sorted(rule)
                .toList();
        return sorted;
    }

    public List<Student> sortPart(Comparator<Student> rule, List<Student> list){
        List<Student> sorted = list.stream()
                .sorted(rule)
                .toList();
        return sorted;
    }

    @Override
    public Map<String, Student> getMap(){
        return Map.copyOf(students);
    }

    @Override
    public void setMap(Map<String, Student> map){
        students = new LinkedHashMap<>(map);
    }
}
