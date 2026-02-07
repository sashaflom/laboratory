package repository;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    // static class variable that will store a reference to a single instance of the class
    private static Repository instance = null;

    private List<Faculty> faculties = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private Repository(){

    }

    // a static class method that should return a reference to a single instance of the class
    public static Repository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new Repository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

}
