package domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDate;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter

public class Teacher extends Person implements Serializable {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    @JsonIdentityReference(alwaysAsId = true)
    private Faculty faculty;
    @JsonIdentityReference(alwaysAsId = true)
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

    public Teacher() {}

    @JsonIgnore
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
            boolean partFields = (Objects.equals(position, teacher.position) &&
                    Objects.equals(academicDegree, teacher.academicDegree) &&
                    Objects.equals(academicTitle, teacher.academicTitle) &&
                    Objects.equals(hireDate, teacher.hireDate) &&
                    workload == teacher.workload);
            boolean faculties = false;
            if ((faculty != null && teacher.faculty == null) || (faculty == null && teacher.faculty != null)){
                faculties = false;
            } else if(faculty == null && teacher.faculty == null){
                faculties = true;
            } else{
                faculties = Objects.equals(faculty.getFullName(), teacher.faculty.getFullName());
            }

            boolean departments = false;
            if ((department != null && teacher.department == null) || (department == null && teacher.department != null)){
                departments = false;
            } else if(department == null && teacher.department == null){
                departments = true;
            } else{
                departments = Objects.equals(department.getName(), teacher.department.getName());
            }
            return partFields && faculties && departments;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), (faculty != null ? faculty.getFullName() : null), (department != null ? department.getName() : null),
                academicDegree, academicTitle, hireDate, workload);
    }
}


