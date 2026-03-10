package repositories;

import domain.*;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.StudentService;
import validators.PersonValidator;

import java.time.LocalDate;
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
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, LocalDate.of(2020,7,22), 1.0 );

        Teacher teacher2 = new Teacher("5498", "P",
                "G", "E", LocalDate.of(2000,3, 14), " n@ukma.edu.u",
                "+380968605533", department, Position.HEAD,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR, LocalDate.of(2021,8,9), 2.0);
        faculty.setDean(teacher1);
        department.setHeadOfDepartment(teacher2);
    }
    @Test
    public void studentAddToRepo(){
        // Arrange
        int initialSize = StudentService.getAll().size();
        // Act
        StudentService.createNewAndAdd("12345", "Y", "N", "I", LocalDate.of(2007, 6, 14),
                "y@ukma.edu.ua", "+380967201296", department, "12345678",
                1, 89, 2024,   EducationForm.BUDGET, StudentStatus.STUDYING );
        // Assert
        int newSize = StudentService.getAll().size();
        assertEquals(initialSize + 1, newSize);
    }

    @Test
    public void studentFindById(){
        // Arrange
        Student student = StudentService.createNewAndAdd("55555", "Т", "А", "С",  LocalDate.of(2008,6, 21),
                "ppp@ukma.edu.ua", "+380967007798", department, "88888888",
                2, 99, 2024,   EducationForm.CONTRACT, StudentStatus.ACADEMIC_LEAVE );
        // Act
        Optional<Student> foundStudent = StudentService. findById(student.getId());
        // Assert
        assertTrue(foundStudent.isPresent());
    }
    @Test
    public void isEmailValidShouldThrowException(){
        String email = "student@gmail.com";
        assertThrows(InvalidEmailException.class, () -> PersonValidator.isEmailValid(email));
    }
    @Test
    public void isPhoneNumberValidShouldThrowException(){
        String phone = "+38095594abdc";
        assertThrows(InvalidPhoneNumber.class, () -> PersonValidator.isPhoneNumberValid(phone));
    }
    @Test
    public void deleteStudent(){
        // Arrange
        Student student = StudentService.createNewAndAdd("11111", "H", "F", "L",  LocalDate.of(2006, 5, 11),
                "uu@ukma.edu.ua", "+380988007711", department, "22222222",
                4, 9, 2022,   EducationForm.CONTRACT, StudentStatus.ACADEMIC_LEAVE );
        //Act
        StudentService.delete(student);
        // Assert
        assertTrue(StudentService.findById(student.getId()).isEmpty());
    }

}
