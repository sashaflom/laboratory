package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

@Getter
@Setter
public class Department {

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