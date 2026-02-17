package domain;

import repositories.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Student extends Person {
    private Department department;
    private String studentId;
    private int course;
    private String group;
    private int enrollmentYear;
    private EducationForm educationForm;  // enum
    private StudentStatus status; // enum

    public Student(String id, String lastName, String firstName, String patronymic,
                   String birthDate, String email, String phone,
                   Department department, String studentId, int course, String group,
                   int enrollmentYear, EducationForm educationForm,
                   StudentStatus status) {
        super(id, lastName, firstName, patronymic, birthDate, email, phone);
        this.department = department;
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        this.enrollmentYear = enrollmentYear;
        this.educationForm = educationForm;
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }
    public String getStudentId() { return studentId; }
    public int getCourse() { return course; }
    public String getGroup() { return group; }
    public int getEnrollmentYear() { return enrollmentYear; }
    public EducationForm getEducationForm() { return educationForm; }
    public StudentStatus getStatus() { return status; }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setCourse(int course) { this.course = course; }
    public void setGroup(String group) { this.group = group; }
    public void setEnrollmentYear(int enrollmentYear) { this.enrollmentYear = enrollmentYear; }
    public void setEducationForm(EducationForm educationForm) { this.educationForm = educationForm; }
    public void setStatus(StudentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Студент: %s, Кафедра: %s, ID студента: %s, Курс: %d, Група: %s, Рік вступу: %d, Форма навчання: %s, Статус: %s",
                        super.toString(),
                        (department != null ? department.getName() : "не призначено"),
                        studentId, course, group, enrollmentYear,
                        educationForm.getDisplayName(),
                        status.getDisplayName());
    }
}



    

