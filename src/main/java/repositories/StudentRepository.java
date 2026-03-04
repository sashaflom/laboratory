package repositories;

import domain.Student;

import java.util.*;
import java.util.function.Predicate;

public class StudentRepository implements Repository<Student, String> {

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

    public Optional <Student> findByStudentId(String id) {
        Optional<Student> foundStudent = Optional.empty();
        if(students.size()!=0){
            for (Student student : students.values()) {
                if(student.getStudentId().equals(id)){
                    foundStudent = Optional.of(student);
                    break;
                }
            }
        }
        return foundStudent;
    }

    public List<Student> findByFullName(Predicate<Student> rule) {
        List<Student> foundStudents = new ArrayList<>();
        for(Student student : students.values()){
            if(rule.test(student)){
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    public List<Student> findByCourse(Predicate<Student> rule){
        List<Student> foundStudents = new ArrayList<>();
        for(Student student : students.values()){
            if(rule.test(student)){
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    public List<Student> findByGroup(Predicate<Student> rule){
        List<Student> foundStudents = new ArrayList<>();
        for(Student student : students.values()){
            if(rule.test(student)){
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(students.values());
    }

    public boolean studentsIsNotEmpty(){
        return students.size()!=0;
    }

}
