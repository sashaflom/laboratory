package domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends Person {
    @JsonIdentityReference(alwaysAsId = true)
    private Faculty faculty;
    @JsonIdentityReference(alwaysAsId = true)
    private Department department;
    private String studentId;
    private int course;
    private int group;
    private int enrollmentYear;
    private EducationForm educationForm;  // enum
    private StudentStatus status; // enum

    public Student(String id, String lastName, String firstName, String patronymic,
                   LocalDate birthDate, String email, String phone,
                   Department department, String studentId, int course, int group,
                   int enrollmentYear, EducationForm educationForm,
                   StudentStatus status) {
        super(id, lastName, firstName, patronymic, birthDate, email, phone);
        if(department==null){
            faculty = null;
        }else{
            faculty = department.getFaculty();
        }
        this.department = department;
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        this.enrollmentYear = enrollmentYear;
        this.educationForm = educationForm;
        this.status = status;
    }

    public Student() {}
    @Override
    public String toString() {
        return String.format("Студент: %s, курс: '%d', факультет: '%s', кафедра: '%s', номер залікової книжки: '%s', група: '%s', рік вступу: '%d', форма навчання: '%s', статус: '%s'",
                        super.toString(), course, (faculty != null ? faculty.getFullName() : "не призначено"),
                (department != null ? department.getName() : "не призначено"),
                        studentId, group, enrollmentYear,
                        educationForm.getDisplayName(),
                        status.getDisplayName());
    }

    @Override
    public boolean equals(Object o){
        if (super.equals(o)){
            Student student = (Student) o;
            return (Objects.equals(faculty.getFullName(), student.faculty.getFullName()) &&
                    Objects.equals(department.getName(), student.department.getName()) &&
                    Objects.equals(studentId, student.studentId) &&
                    course == student.course &&
                    group == student.group &&
                    enrollmentYear == student.enrollmentYear &&
                    Objects.equals(educationForm, student.educationForm) &&
                    Objects.equals(status, student.status));
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), faculty.getFullName(), department.getName(), studentId,
                course, group, enrollmentYear, educationForm, status);
    }
}



    

