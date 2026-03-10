package domain;

import java.util.Objects;

public class Department {

    private final String id;
    private String name;
    private Faculty faculty;
    private Teacher headOfDepartment;
    private String location;

    public Department(String id, String name, Faculty faculty, Teacher headOfDepartment, String location){
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.headOfDepartment = headOfDepartment;
        this.location = location;
    }

    @Override
    public String toString(){
        return "Кафедра: ID: '%s', назва: '%s', факультет: '%s', завідувач: '%s', локація: '%s'.".formatted(id, name,(faculty != null ? faculty.getFullName() : "не призначено"),
                (headOfDepartment != null ? headOfDepartment.getFullName() : "не призначено"), location);
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        Department department = (Department) o;
        return (Objects.equals(id, department.id) &&
                Objects.equals(name, department.name) &&
                Objects.equals(faculty.getFullName(), department.faculty.getFullName()) &&
                Objects.equals(headOfDepartment.getId(), department.headOfDepartment.getId()) &&
                Objects.equals(location, department.location));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, faculty.getFullName(), headOfDepartment.getId(),
                location);
    }
}