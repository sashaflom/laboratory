package domain;

import repositories.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Department {

    private final String uniqueCode;
    private String name;
    private Faculty faculty;
    private Teacher headOfDepartment;
    private String location;

    public Department(String uniqueCode, String name, Faculty faculty, Teacher headOfDepartment, String location){
        this.uniqueCode = uniqueCode;
        this.name = name;
        this.faculty = faculty;
        this.headOfDepartment = headOfDepartment;
        this.location = location;
    }

    @Override
    public String toString(){
        return "\nКафедра: \nунікальний код: '%s', \nназва: '%s', \nфакультет: '%s', \nзавідувач: '%s', \nлокація: '%s'.".formatted(uniqueCode, name,(faculty != null ? faculty.getFullName() : "не призначено"),
                (headOfDepartment != null ? headOfDepartment.getFullName() : "не призначено"), location);
    }

    public String getUniqueCode() {
        return uniqueCode;
    }
    public String getName() {
        return name;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public Teacher getHeadOfDepartment() {
        return headOfDepartment;
    }
    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setHeadOfDepartment(Teacher headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}