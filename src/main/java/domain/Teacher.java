package domain;

import repositories.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Teacher extends Person {

    private Department department;
    private Position position; //посада
    private AcademicDegree academicDegree; //науковий ступінь
    private AcademicTitle academicTitle; // enum, вчене звання
    private String hireDate; //дата прийняття на роботу
    private double workload; //ставка/навантаження

    public Teacher(String id, String lastName, String firstName,String patronymic,
                  String birthDate, String email, String phoneNumber, Department department, Position position,
                  AcademicDegree academicDegree, AcademicTitle academicTitle,
                  String hireDate, double workload ) {
       super(id, lastName, firstName, patronymic, birthDate, email, phoneNumber);
       this.department = department;
       this.position = position;
       this.academicDegree = academicDegree;
       this.academicTitle = academicTitle;
       this.hireDate = hireDate;
       this.workload = workload;
   }

    public Department getDepartment() {
        return department;
    }
    public Position getPosition() {return position;}
    public AcademicDegree getAcademicDegree() {return academicDegree;}
    public AcademicTitle getAcademicTitle() {return academicTitle;}
    public String getHireDate() {return hireDate;}
    public double getWorkload() {return workload;}

    public void setDepartment(Department department) {
        this.department = department;
    }
    public void setPosition(Position position) {this.position = position;}
    public void setAcademicDegree(AcademicDegree academicDegree) { this.academicDegree = academicDegree;}
    public void setAcademicTitle(AcademicTitle academicTitle) { this.academicTitle = academicTitle;}
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
    public void setWorkload(double workload) {this.workload = workload;}

    @Override
    public String toString() {
        return String.format("Викладач: %s, Кафедра: %s, Посада: %s, Науковий ступінь: %s, Вчене звання: %s, Дата прийняття: %s, Ставка: %s",
                        super.toString(), (department != null ? department.getName() : "не призначено"), position.getDisplayName(), academicDegree.getDisplayName(), academicTitle.getDisplayName(),
                        hireDate, workload);
    }
}


