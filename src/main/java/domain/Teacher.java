package domain;

import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDate;

public class Teacher extends Person {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private Faculty faculty;
    private Department department;
    private Position position; //посада
    private AcademicDegree academicDegree; //науковий ступінь
    private AcademicTitle academicTitle; // enum, вчене звання
    private LocalDate hireDate; //дата прийняття на роботу
    private double workload; //ставка/навантаження

    public Teacher(String id, String lastName, String firstName,String patronymic,
                  LocalDate birthDate, String email, String phoneNumber, Department department, Position position,
                  AcademicDegree academicDegree, AcademicTitle academicTitle,
                   LocalDate hireDate, double workload ) {
       super(id, lastName, firstName, patronymic, birthDate, email, phoneNumber);
       if(department==null){
           faculty = null;
       }else{
           faculty = department.getFaculty();
       }
       this.department = department;
       this.position = position;
       this.academicDegree = academicDegree;
       this.academicTitle = academicTitle;
       this.hireDate = hireDate;
       this.workload = workload;
   }

    public Faculty getFaculty() {
        return faculty;
    }
    public Department getDepartment() {
        return department;
    }
    public Position getPosition() {return position;}
    public AcademicDegree getAcademicDegree() {return academicDegree;}
    public AcademicTitle getAcademicTitle() {return academicTitle;}
    public LocalDate getHireDate() {return hireDate;}
    public double getWorkload() {return workload;}


    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setPosition(Position position) {this.position = position;}
    public void setAcademicDegree(AcademicDegree academicDegree) { this.academicDegree = academicDegree;}
    public void setAcademicTitle(AcademicTitle academicTitle) { this.academicTitle = academicTitle;}
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public void setWorkload(double workload) {this.workload = workload;}

    public int getWorkExperience() {
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("Викладач: %s, факультет: '%s', кафедра: '%s', посада: '%s', науковий ступінь: '%s', вчене звання: '%s', дата прийняття: '%s', стаж: '%d', ставка: '%s'",
                        super.toString(), (faculty != null ? faculty.getFullName() : "не призначено"),
                (department != null ? department.getName() : "не призначено"), position.getDisplayName(), academicDegree.getDisplayName(), academicTitle.getDisplayName(),
                        hireDate.format(FORMATTER),getWorkExperience(), workload);
    }

    @Override
    public boolean equals(Object o){
        if (super.equals(o)){
            Teacher teacher = (Teacher) o;
            return (Objects.equals(faculty.getFullName(), teacher.faculty.getFullName()) &&
                    Objects.equals(department.getName(), teacher.department.getName()) &&
                    Objects.equals(position, teacher.position) &&
                    Objects.equals(academicDegree, teacher.academicDegree) &&
                    Objects.equals(academicTitle, teacher.academicTitle) &&
                    Objects.equals(hireDate, teacher.hireDate) &&
                    workload == teacher.workload);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), faculty.getFullName(), department.getName(), position,
                academicDegree, academicTitle, hireDate, workload);
    }
}


