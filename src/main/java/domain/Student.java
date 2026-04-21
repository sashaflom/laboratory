package domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person implements Serializable {
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

    public Faculty getFaculty() {
        return faculty;
    }
    public Department getDepartment() {
        return department;
    }
    public String getStudentId() { return studentId; }
    public int getCourse() { return course; }
    public int getGroup() { return group; }
    public int getEnrollmentYear() { return enrollmentYear; }
    public EducationForm getEducationForm() { return educationForm; }
    public StudentStatus getStatus() { return status; }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setCourse(int course) { this.course = course; }
    public void setGroup(int group) { this.group = group; }
    public void setEnrollmentYear(int enrollmentYear) { this.enrollmentYear = enrollmentYear; }
    public void setEducationForm(EducationForm educationForm) { this.educationForm = educationForm; }
    public void setStatus(StudentStatus status) { this.status = status; }

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
            boolean partFields = (Objects.equals(studentId, student.studentId) &&
                    course == student.course &&
                    group == student.group &&
                    enrollmentYear == student.enrollmentYear &&
                    Objects.equals(educationForm, student.educationForm) &&
                    Objects.equals(status, student.status));
            boolean faculties = false;
            if ((faculty != null && student.faculty == null) || (faculty == null && student.faculty != null)){
                faculties = false;
            } else if(faculty == null && student.faculty == null){
                faculties = true;
            } else{
                faculties = Objects.equals(faculty.getFullName(), student.faculty.getFullName());
            }

            boolean departments = false;
            if ((department != null && student.department == null) || (department == null && student.department != null)){
                departments = false;
            } else if(department == null && student.department == null){
                departments = true;
            } else{
                departments = Objects.equals(department.getName(), student.department.getName());
            }
            return partFields && faculties && departments;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), (faculty != null ? faculty.getFullName() : null), (department != null ? department.getName() : null), studentId,
                course, group, enrollmentYear, educationForm, status);
    }
}



    

