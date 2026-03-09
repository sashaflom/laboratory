package repositories;
import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validators.PersonValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class StudentRepositoryTest {
    Department department;
    Faculty faculty;
    @BeforeEach
    public void setUp() {
        faculty = new Faculty("1019", "Факультет Інформатики",
                "ФІ",null , "098");

        department = new Department("4321", "Кафедра математики",
                faculty, null, "000");

        Teacher teacher1 = new Teacher("13245", "V",
                "L", "F", LocalDate.of(1999, 6, 14), " V@ukma.edu.u",
                "+380961457766", department,  Position.DEAN,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, "12.07.2020", 1.0 );

        Teacher teacher2 = new Teacher("5498", "P",
                "G", "E", LocalDate.of(2000,3, 14), " n@ukma.edu.u",
                "+380968605533", department, Position.HEAD,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR, "12.07.2020", 2.0);
        faculty.setDean(teacher1);
        department.setHeadOfDepartment(teacher2);
    }
    @Test
    public void studentAddToRepo(){
        // Arrange
        StudentRepository repository = StudentRepository.getInstance();
        int initialSize = repository.getAll().size();
        Student newStudent = new Student("12345", "Y", "N", "I", LocalDate.of(2007, 6, 14),
                "y@ukma.edu.ua", "+380967201296", department, "12345678",
                1, 89, 2024,   EducationForm.BUDGET, StudentStatus.STUDYING );
        //Act
        repository.add(newStudent);
        // Assert
        int newSize = repository.getAll().size();
        assertEquals(initialSize + 1, newSize, "Розмір списку мав збільшитися на 1");
    }

    @Test
    public void studentFindById(){
        // Arrange
        StudentRepository repository = StudentRepository.getInstance();
        Student newStudent = new Student("55555", "Т", "А", "С",  LocalDate.of(2008,6, 21),
                "ppp@ukma.edu.ua", "+380967007798", department, "88888888",
                2, 99, 2024,   EducationForm.CONTRACT, StudentStatus.ACADEMIC_LEAVE );
        //Act
        repository.add(newStudent);
        // Assert
        Optional<Student> foundStudent = repository.findById(newStudent.getId());
        assertTrue(foundStudent.isPresent());
        assertEquals(newStudent.getStudentId(), foundStudent.get().getStudentId());
    }
    @Test
    public void validEmailShouldReturnTrue(){
        String email = "student@ukma.edu.ua";
        assertTrue(PersonValidator.isEmailValid(email));
    }
    @Test
    public void validEmailShouldReturnFalse(){
        String email = "student@gmail.com";
        assertFalse(PersonValidator.isEmailValid(email));
    }
    @Test
    public void deleteStudent(){
        // Arrange
        StudentRepository repository = StudentRepository.getInstance();
        Student newStudent = new Student("11111", "H", "F", "L",  LocalDate.of(2006, 5, 11),
                "uu@ukma.edu.ua", "+380988007711", department, "22222222",
                4, 9, 2022,   EducationForm.CONTRACT, StudentStatus.ACADEMIC_LEAVE );
        //Act
        repository.add(newStudent);
        assertTrue(repository.findById(newStudent.getId()).isPresent());
        repository.delete(newStudent);
        // Assert
        assertFalse(repository.findById(newStudent.getId()).isPresent());
    }

}
