package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Objects;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Department implements Serializable {

    private final String id;
    private String name;
    @JsonIdentityReference(alwaysAsId = true)
    private Faculty faculty;
    @JsonIdentityReference(alwaysAsId = true)
    private Teacher headOfDepartment;
    private String location;

    public Department(String id, String name, Faculty faculty, Teacher headOfDepartment, String location){
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.headOfDepartment = headOfDepartment;
        this.location = location;
    }

    public Department() {
        this.id = null;
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
    public String getLocation() {return location;}

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
        boolean partFields = (Objects.equals(id, department.id) &&
                Objects.equals(name, department.name) &&
                Objects.equals(location, department.location));
        boolean faculties = false;
        if ((faculty == null && department.faculty != null) || (faculty != null && department.faculty == null)){
            faculties = false;
        } else if (faculty == null && department.faculty == null){
            faculties = true;
        } else{
            faculties = Objects.equals(faculty.getFullName(), department.faculty.getFullName());
        }

        boolean heads = false;
        if ((headOfDepartment == null && department.headOfDepartment != null) || (headOfDepartment != null && department.headOfDepartment == null)){
            heads = false;
        } else if (headOfDepartment == null && department.headOfDepartment == null){
            heads = true;
        } else{
            heads = Objects.equals(headOfDepartment.getId(), department.headOfDepartment.getId());
        }

        return partFields && faculties && heads;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, (faculty != null ? faculty.getFullName() : null), (headOfDepartment != null ? headOfDepartment.getId() : null),
                location);
    }
}