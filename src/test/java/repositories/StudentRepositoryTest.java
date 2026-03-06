package repositories;
import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class StudentRepositoryTest {
    Department department;
    Faculty faculty;
    @BeforeEach
    public void setUp() {
        Faculty faculty = new Faculty("1019", "Факультет Інформатики",
                "ФІ",null , "098");

        department = new Department("4321", "Кафедра математики",
                faculty, null, "000");

        Teacher teacher1 = new Teacher("13245", "V",
                "L", "F", "14.06", " V@ukma.edu.u",
                "+380961457766", department,  Position.DEAN,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, "12.07.2020", 1.0 );

        Teacher teacher2 = new Teacher("5498", "P",
                "G", "E", "14.03", " n@ukma.edu.u",
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
        Student newStudent = new Student("12345", "Y", "N", "I", "14.06.2007",
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
        StudentRepository repository = StudentRepository.getInstance();
    }

}
