package services;

import data.DataService;
import domain.*;
import network.UniversityClient;
import repositories.TeacherRepository;

import java.util.*;
import java.time.LocalDate;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeacherService {

    private static TeacherRepository repository = TeacherRepository.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public static Teacher createNewAndAdd(String id, String lastName, String firstName, String patronymic, LocalDate birthDate,
                                          String email, String phoneNumber, Department department, Position position,
                                          AcademicDegree academicDegree, AcademicTitle academicTitle, LocalDate hireDate, double workload){
        Teacher teacher = new Teacher(id, lastName, firstName, patronymic, birthDate, email, phoneNumber, department, position,
                academicDegree, academicTitle, hireDate, workload);

        try {
            ValidationService.validate(teacher);
            logger.info("Викладач із валідними даними: {}", teacher);
        } catch (RuntimeException e) {
            logger.error("Помилка, невалідні дані для викладача: {}; {}", e.getMessage(), teacher);
            System.out.println(e.getMessage());
            return null;
        }
        repository.add(teacher);
        return teacher;
    }

    public static void delete(Teacher teacher){
        if (teacher.getPosition().equals(Position.DEAN)){
            List<Faculty> faculties = FacultyService.getAll();
            if (!faculties.isEmpty()){
                for (Faculty faculty : faculties){
                    if (faculty.getDean() != null && faculty.getDean().equals(teacher)){
                        faculty.setDean(null);
                        break;
                    }
                }
            }
        } else if (teacher.getPosition().equals(Position.HEAD)){
            List<Department> departments = DepartmentService.getAll();
            if (!departments.isEmpty()){
                for (Department department : departments){
                    if (department.getHeadOfDepartment() != null && department.getHeadOfDepartment().equals(teacher)){
                        department.setHeadOfDepartment(null);
                        break;
                    }
                }
            }
        }
        repository.delete(teacher);
    }

    public static Optional<Teacher> findById(String id){
        return repository.findById(id);
    }

    public static Teacher findLastAdded(){
        return repository.findLastAdded();
    }

    public static List<Teacher> findByFullName(String fullName){
        return repository.findAll(t -> t.getFullName().equals(fullName));
    }

    public static List<Teacher> findAllByDepartment(Department department){
        Predicate<Teacher> rule = teacher -> teacher.getDepartment() != null && teacher.getDepartment().equals(department);
        return repository.findAll(rule);
    }

    public static List<Teacher> getAllTeachersSortedByAlphabet(){
        List<Teacher> all = new ArrayList<>(repository.findAll(t ->true));
        all.sort((t1, t2) -> t1.getFullName().compareTo(t2.getFullName()));
        return all;
    }
    public static List<Teacher> getAllTeachersSortedByDepartment(Department department){
        List<Teacher> teachersList = new ArrayList<>(repository.findAll (teacher -> teacher.getDepartment() != null && teacher.getDepartment().equals(department)));
        teachersList.sort((t1,t2)-> t1.getFullName().compareTo(t2.getFullName()));
        return teachersList;
    }
    public static List<Teacher> getAllTeachersSortedByFaculty(Faculty faculty){
        List<Teacher> teachersList1 = new ArrayList<>(repository.findAll (teacher1 -> teacher1.getFaculty() != null && teacher1.getFaculty().equals(faculty)));
        teachersList1.sort((t1, t2) -> t1.getFullName().compareTo(t2.getFullName()));
        return teachersList1;
    }

    public static List<Teacher> getAll(){
        return repository.getAll();
    }

    public static boolean isThereSomethingInRepository(){
        return repository.teachersIsNotEmpty();
    }

    public static void changeFacultyInDepartment (Department department, Faculty faculty){
        List<Teacher> teachers = findAllByDepartment(department);
        if (!teachers.isEmpty()){
            for (Teacher teacher : teachers){
                teacher.setFaculty(faculty);
            }
        }
    }

}
