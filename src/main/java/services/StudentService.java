package services;

import domain.*;
import repositories.StudentRepository;

import java.util.*;
import java.util.function.Predicate;
import java.time.LocalDate;


public class StudentService {

    private static StudentRepository repository = StudentRepository.getInstance();

    public static Student createNewAndAdd(String id, String lastName, String firstName, String patronymic, LocalDate birthDate,
                                   String email, String phoneNumber, Department department, String studentId, int course,
                                   int group, int enrollmentYear, EducationForm educationForm, StudentStatus status){
        Student student = new Student(id, lastName, firstName, patronymic, birthDate, email, phoneNumber, department, studentId,
                course, group, enrollmentYear, educationForm, status);

        try {
            ValidationService.validate(student);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        repository.add(student);
        return student;
    }

    public static void delete(Student student){
        repository.delete(student);
    }

    public static Optional<Student> findById(String id){
        return repository.findById(id);
    }

    public static List<Student> findAllByFullName(String fullName){
        Predicate<Student> rule = student -> student.getFullName().equals(fullName);
        return repository.findAll(rule);
    }

    public static List<Student> findAllByCourse(int course){
        Predicate<Student> rule = student -> student.getCourse() == course;
        return repository.findAll(rule);
    }

    public static List<Student> findPartByCourse(int course, List<Student> students){
        Predicate<Student> rule = student -> student.getCourse() == course;
        return repository.findPart(rule, students);
    }

    public static List<Student> findAllByGroup(int group){
        Predicate<Student> rule = student -> student.getGroup() == group;
        return repository.findAll(rule);
    }

    public static List<Student> findAllByDepartment(Department department){
        Predicate<Student> rule = student -> student.getDepartment() != null && student.getDepartment().equals(department);
        return repository.findAll(rule);
    }

    public static List<Student> findAllByFaculty(Faculty faculty){
        Predicate<Student> rule = student -> student.getFaculty() != null && student.getFaculty().equals(faculty);
        return repository.findAll(rule);
    }

    public static List<Student> getAll(){
        return repository.getAll();
    }

    public static boolean isThereSomethingInRepository(){
        return repository.studentsIsNotEmpty();
    }

    public static List<Student> sortAllByCourse(){
        Comparator<Student> rule = (s1, s2) -> Integer.compare(s1.getCourse(), s2.getCourse());
        return repository.sortAll(rule);
    }

    public static List<Student> sortPartByCourse(List<Student> students){
        Comparator<Student> rule = (s1, s2) -> Integer.compare(s1.getCourse(), s2.getCourse());
        return repository.sortPart(rule, students);
    }

    public static List<Student> sortAllByAlphabet(){
        Comparator<Student> rule = (s1, s2) -> s1.getFullName().compareTo(s2.getFullName());
        return repository.sortAll(rule);
    }

    public static List<Student> sortPartByAlphabet(List<Student> students){
        Comparator<Student> rule = (s1, s2) -> s1.getFullName().compareTo(s2.getFullName());
        return repository.sortPart(rule, students);
    }

    public static void changeFacultyInDepartment (Department department, Faculty faculty){
        List<Student> students = findAllByDepartment(department);
        if (!students.isEmpty()){
            for (Student student : students){
                student.setFaculty(faculty);
            }
        }
    }
}
