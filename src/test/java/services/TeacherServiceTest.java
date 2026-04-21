package services;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TeacherRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherServiceTest {

    private Faculty faculty1;
    private Faculty faculty2;
    private Department department1;
    private Department department2;

    @BeforeEach
    void setUp() {
        TeacherRepository.getInstance().setMap(new LinkedHashMap<>());

        faculty1 = new Faculty("101", "Факультет інформатики", "ФІ", null, "098");
        faculty2 = new Faculty("102", "Факультет гуманітарних наук", "ФГН", null, "099");

        department1 = new Department("201", "Кафедра математики", faculty1, null, "101");
        department2 = new Department("202", "Кафедра історії", faculty2, null, "202");
    }

    @Test
    void addShouldIncreaseSize() {
        int initialSize = TeacherService.getAll().size();

        Teacher teacher = TeacherService.createNewAndAdd(
                "1001", "Іваненко", "Петро", "Олегович",
                LocalDate.of(1980, 5, 10),
                "teacher1@ukma.edu.ua", "+380961111111",
                department1, Position.DEAN,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR,
                LocalDate.of(2010, 9, 1), 1.0
        );

        int newSize = TeacherService.getAll().size();

        assertNotNull(teacher);
        assertEquals(initialSize + 1, newSize);
    }

    @Test
    void findByIdReturnWhenExists() {
        Teacher teacher = TeacherService.createNewAndAdd(
                "1002", "Петренко", "Іван", "Степанович",
                LocalDate.of(1975, 3, 12),
                "teacher2@ukma.edu.ua", "+380962222222",
                department1, Position.HEAD,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2012, 8, 15), 0.75
        );

        Optional<Teacher> foundTeacher = TeacherService.findById(teacher.getId());

        assertTrue(foundTeacher.isPresent());
        assertEquals(teacher.getId(), foundTeacher.get().getId());
    }

    @Test
    void findByIdReturnEmptyWhenNotExists() {
        Optional<Teacher> foundTeacher = TeacherService.findById("9999");

        assertTrue(foundTeacher.isEmpty());
    }

    @Test
    void deleteRemoveTeacher() {
        Teacher teacher = TeacherService.createNewAndAdd(
                "1003", "Сидоренко", "Марія", "Ігорівна",
                LocalDate.of(1988, 7, 1),
                "teacher3@ukma.edu.ua", "+380963333333",
                department1, Position.DEAN,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2015, 2, 10), 1.0
        );

        TeacherService.delete(teacher);

        assertTrue(TeacherService.findById(teacher.getId()).isEmpty());
    }

    @Test
    void findLastAdded() {
        TeacherService.createNewAndAdd(
                "1004", "Андрієнко", "Олена", "Василівна",
                LocalDate.of(1982, 11, 21),
                "teacher4@ukma.edu.ua", "+380964444444",
                department1, Position.HEAD,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR,
                LocalDate.of(2008, 9, 1), 1.0
        );

        Teacher lastTeacher = TeacherService.createNewAndAdd(
                "1005", "Бойко", "Анна", "Сергіївна",
                LocalDate.of(1990, 4, 4),
                "teacher5@ukma.edu.ua", "+380965555555",
                department2, Position.DEAN,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2018, 1, 20), 0.5
        );

        Teacher foundLast = TeacherService.findLastAdded();

        assertNotNull(foundLast);
        assertEquals(lastTeacher.getId(), foundLast.getId());
    }

    @Test
    void findByFullName() {
        Teacher teacher = TeacherService.createNewAndAdd(
                "1006", "Коваленко", "Ірина", "Петрівна",
                LocalDate.of(1985, 6, 30),
                "teacher6@ukma.edu.ua", "+380966666666",
                department1, Position.DEAN,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2014, 9, 1), 1.0
        );

        List<Teacher> result = TeacherService.findByFullName(teacher.getFullName());

        assertEquals(1, result.size());
        assertEquals(teacher.getFullName(), result.get(0).getFullName());
    }

    @Test
    void findAllByDepartment() {
        Teacher teacher1 = TeacherService.createNewAndAdd(
                "1007", "А", "А", "А",
                LocalDate.of(1980, 1, 1),
                "a@ukma.edu.ua", "+380967777777",
                department1, Position.DEAN,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR,
                LocalDate.of(2010, 1, 1), 1.0
        );

        Teacher teacher2 = TeacherService.createNewAndAdd(
                "1008", "Б", "Б", "Б",
                LocalDate.of(1981, 2, 2),
                "b@ukma.edu.ua", "+380968888888",
                department2, Position.HEAD,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2011, 2, 2), 1.0
        );

        List<Teacher> result = TeacherService.findAllByDepartment(department1);

        assertEquals(1, result.size());
        assertEquals(teacher1.getId(), result.get(0).getId());
        assertNotEquals(teacher2.getId(), result.get(0).getId());
    }

    @Test
    void getAllTeachersSortedByAlphabet() {
        TeacherService.createNewAndAdd(
                "1009", "Яковенко", "Олег", "Іванович",
                LocalDate.of(1983, 8, 8),
                "teacher9@ukma.edu.ua", "+380969999999",
                department1, Position.DEAN,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR,
                LocalDate.of(2010, 9, 1), 1.0
        );

        TeacherService.createNewAndAdd(
                "1010", "Бондар", "Андрій", "Петрович",
                LocalDate.of(1984, 9, 9),
                "teacher10@ukma.edu.ua", "+380970000000",
                department1, Position.HEAD,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2011, 9, 1), 1.0
        );

        List<Teacher> result = TeacherService.getAllTeachersSortedByAlphabet();

        assertEquals(2, result.size());
        assertTrue(result.get(0).getFullName().compareTo(result.get(1).getFullName()) < 0);
    }

    @Test
    void changeFacultyInDepartment() {
        Teacher teacher1 = TeacherService.createNewAndAdd(
                "1011", "Гнатюк", "Ірина", "Олексіївна",
                LocalDate.of(1986, 10, 10),
                "teacher11@ukma.edu.ua", "+380971111111",
                department1, Position.DEAN,
                AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR,
                LocalDate.of(2013, 3, 3), 1.0
        );

        Teacher teacher2 = TeacherService.createNewAndAdd(
                "1012", "Денисенко", "Роман", "Вікторович",
                LocalDate.of(1987, 11, 11),
                "teacher12@ukma.edu.ua", "+380972222222",
                department1, Position.HEAD,
                AcademicDegree.CANDIDATE, AcademicTitle.DOCENT,
                LocalDate.of(2014, 4, 4), 0.75
        );

        TeacherService.changeFacultyInDepartment(department1, faculty2);

        assertEquals(faculty2, teacher1.getFaculty());
        assertEquals(faculty2, teacher2.getFaculty());
    }

    @Test
    void isThereSomethingInRepository() {
        assertFalse(TeacherService.isThereSomethingInRepository());
    }
}