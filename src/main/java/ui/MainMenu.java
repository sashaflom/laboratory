package ui;

import domain.*;
import repositories.DepartmentRepository;
import repositories.FacultyRepository;
import repositories.StudentRepository;
import repositories.TeacherRepository;
import services.DepartmentService;
import services.FacultyService;
import services.StudentService;
import services.TeacherService;
import validators.InputReader;

import java.time.LocalDate;
import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        University kyivMohylaAcademy = new University("Національний університет 'Києво-Могилянська академія'",
                "НаУКМА", "Київ", "вулиця Григорія Сковороди, 2, Київ, 04655");
        System.out.println("Вітаю в інформаційній системі Києво-Могилянської академії!");
        System.out.println("\nСтворено:" + kyivMohylaAcademy);
        // test
        Teacher fenDean = new Teacher ("11111", "Глущенко", "Олександра","Степанівна", LocalDate.of(1972,4,12), "hlushchenkoas@ukma.edu.ua", "+380956387102", null, Position.DEAN, AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR, LocalDate.of(2005,9,1), 48000);
        Faculty fen = new Faculty ("1111111", "Факультет економічних наук", "ФЕН", fenDean, "6-403, +380444256042");
        Teacher financeHead = new Teacher ("33333", "Слав'янська", "Наталія","Миколаївна", LocalDate.of(1965, 8,25), "slavyanska.nm@ukma.edu.ua", "+380444256042", null, Position.HEAD, AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, LocalDate.of(1998,8,15), 36500);
        Department finance = new Department ("1111", "Кафедра фінансів", fen, financeHead, "6-404, +380444635927");
        fenDean.setDepartment(finance);
        fenDean.setFaculty(fenDean.getDepartment().getFaculty());
        financeHead.setDepartment(finance);
        financeHead.setFaculty(fenDean.getDepartment().getFaculty());
        Teacher marketingHead = new Teacher ("44444", "Фаріон", "Наталія","Миколаївна", LocalDate.of(1978,11,5), "farion.nm@ukma.edu.ua", "+380444635928", null, Position.HEAD, AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, LocalDate.of(2001,1,9), 34000);
        Department marketing = new Department ("2222", "Кафедра маркетингу та управління бізнесом", fen, marketingHead, "6-412, +380444256042");
        marketingHead.setDepartment(marketing);
        marketingHead.setFaculty(marketingHead.getDepartment().getFaculty());
        Teacher fenTeacher = new Teacher ("77777", "Пічик", "Катерина","Валеріївна", LocalDate.of(1983,6,18), "pichyk.kv@ukma.edu.ua", "+380679998877", marketing, Position.TEACHER, AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, LocalDate.of(2012,1,9), 31500);
        Student finance1 = new Student ("11111", "Ткаченко", "Олена", "Ігорівна", LocalDate.of (2005,5,12), "tkachenko.o@ukma.edu.ua", "+380501234455", finance, "ЕН-23015", 3, 2, 2023, EducationForm.BUDGET, StudentStatus.STUDYING);
        Student finance2 = new Student ("22222", "Бойко", "Вікторія", "Сергіївна", LocalDate.of (2002, 1, 21), "boiko.v@ukma.edu.ua", "+380671112233", finance, "ЕН-2504М", 5, 1, 2025, EducationForm.BUDGET, StudentStatus.ACADEMIC_LEAVE);
        Student finance3 = new Student ("33333", "Козак", "Анастасія", "Іванівна", LocalDate.of(2004, 8,30), "kozak.a@ukma.edu.ua", "+380634449900", finance, "ЕН-22041", 4, 1, 2022, EducationForm.BUDGET, StudentStatus.STUDYING);
        Student marketing1 = new Student ("44444", "Кравченко", "Юрій", "Петрович", LocalDate.of(2003,3,11), "kravchenko.yu@ukma.edu.ua", "+380937776655", marketing, "ЕН-21102", 4, 5, 2021, EducationForm.CONTRACT, StudentStatus.EXPELLED);
        Student marketing2 = new Student ("55555", "Мороз", "Владислав", "Дмитрович", LocalDate.of(2006,2, 14), "moroz.v@ukma.edu.ua", "+380978881122", marketing, "ЕН-24088", 2, 3, 2024, EducationForm.BUDGET, StudentStatus.STUDYING);
        Student marketing3 = new Student ("66666", "Григоренко", "Денис", "Андрійович", LocalDate.of(2003,2,15), "hryhorenko.d@ukma.edu.ua", "+380503337711", marketing, "ЕН-21115", 6, 2, 2024, EducationForm.CONTRACT, StudentStatus.EXPELLED);
        Teacher fiDean = new Teacher ("22222", "Глибовець", "Андрій","Миколайович", LocalDate.of(1975,5,15), "hlybovets@ukma.edu.ua", "+380444256057", null, Position.DEAN, AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR, LocalDate.of(2003,9,1), 45000);
        Faculty fi = new Faculty ("2222222", "Факультет інформатики", "ФІ", fiDean, "1-321, +380444256057");
        Teacher informatikaHead = new Teacher ("55555", "Глибовець", "Микола","Миколайович", LocalDate.of(1948,11,2),"m.hlybovets@ukma.edu.ua", "+380444636931", null, Position.HEAD, AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR, LocalDate.of(1992,9,1), 42000);
        Department informatika = new Department ("3333", "Кафедра інформатики", fi, informatikaHead, "1-319, +380444636931");
        fiDean.setDepartment(informatika);
        fiDean.setFaculty(fiDean.getDepartment().getFaculty());
        informatikaHead.setDepartment(informatika);
        informatikaHead.setFaculty(informatikaHead.getDepartment().getFaculty());
        Teacher merezhkiHead = new Teacher ("66666", "Бублик", "Володимир","Васильович", LocalDate.of (1962,9,3), "v.bublyk@ukma.edu.ua", "+380444238126", null, Position.HEAD, AcademicDegree.CANDIDATE, AcademicTitle.DOCENT, LocalDate.of(2006,12,1), 34800);
        Department merezhki = new Department ("4444", "Кафедра мережевих технологій", fi, merezhkiHead, "1-315, +380444238126");
        merezhkiHead.setDepartment(merezhki);
        merezhkiHead.setFaculty(merezhkiHead.getDepartment().getFaculty());
        Teacher fiTeacher = new Teacher ("88888", "Трохимчук", "Олександр", "Степанович", LocalDate.of(1968, 2, 12), "trokhymchuk@ukma.edu.ua", "+380671234567", merezhki, Position.TEACHER, AcademicDegree.DOCTOR, AcademicTitle.PROFESSOR,LocalDate.of(2001,8,15), 38200);
        Student informatika1 = new Student ("77777", "Коваленко", "Дмитро", "Ігорович", LocalDate.of(2006, 3,22), "kovalenko.d@ukma.edu.ua", "+380631112233", informatika, "ФІ-24012", 2, 1, 2024, EducationForm.BUDGET, StudentStatus.STUDYING);
        Student informatika2 = new Student ("88888", "Шевченко", "Артем", "Віталійович", LocalDate.of(2003,11,30), "shevchenko.a@ukma.edu.ua", "+380997778899", informatika, "ФІ-2508М", 5, 1, 2025, EducationForm.BUDGET, StudentStatus.ACADEMIC_LEAVE);
        Student informatika3 = new Student ("99999", "Лисенко", "Юлія", "Володимирівна", LocalDate.of(2007, 9, 19), "lysenko.yu@ukma.edu.ua", "+380509993344", informatika, "ФІ-25011", 1, 5, 2025, EducationForm.CONTRACT, StudentStatus.STUDYING);
        Student merezhki1 = new Student ("aaaaa", "Мельник", "Анна", "Олександрівна", LocalDate.of(2004, 7,14), "melnyk.a@ukma.edu.ua", "+380954445566", merezhki, "ФІ-22105", 4, 2, 2022, EducationForm.CONTRACT, StudentStatus.STUDYING);
        Student merezhki2 = new Student ("bbbbb", "Бондаренко", "Ігор", "Юрійович", LocalDate.of(2005,5,1), "bondarenko.i@ukma.edu.ua", "+380675551122", merezhki, "ФІ-23045", 3, 3, 2023, EducationForm.BUDGET, StudentStatus.STUDYING);
        Student merezhki3 = new Student ("ccccc", "Павленко", "Максим", "Олегович", LocalDate.of(2002,6,12), "pavlenko.m@ukma.edu.ua", "+380931110099", merezhki, "ФІ-21098", 2, 2, 2024, EducationForm.CONTRACT, StudentStatus.EXPELLED);

        FacultyRepository.getInstance().add(fi);
        FacultyRepository.getInstance().add(fen);

        DepartmentRepository.getInstance().add(finance);
        DepartmentRepository.getInstance().add(marketing);
        DepartmentRepository.getInstance().add(informatika);
        DepartmentRepository.getInstance().add(merezhki);

        TeacherRepository.getInstance().add(fenDean);
        TeacherRepository.getInstance().add(financeHead);
        TeacherRepository.getInstance().add(marketingHead);
        TeacherRepository.getInstance().add(fenTeacher);
        TeacherRepository.getInstance().add(fiDean);
        TeacherRepository.getInstance().add(informatikaHead);
        TeacherRepository.getInstance().add(merezhkiHead);
        TeacherRepository.getInstance().add(fiTeacher);

        StudentRepository.getInstance().add(finance1);
        StudentRepository.getInstance().add(finance2);
        StudentRepository.getInstance().add(finance3);
        StudentRepository.getInstance().add(marketing1);
        StudentRepository.getInstance().add(marketing2);
        StudentRepository.getInstance().add(marketing3);
        StudentRepository.getInstance().add(informatika1);
        StudentRepository.getInstance().add(informatika2);
        StudentRepository.getInstance().add(informatika3);
        StudentRepository.getInstance().add(merezhki1);
        StudentRepository.getInstance().add(merezhki2);
        StudentRepository.getInstance().add(merezhki3);


        while (true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити:" +
                    "\n1 - операції керування даними" +
                    "\n2 - пошук" +
                    "\n3 - звіти" +
                    "\n0 - закінчити", 0, 3);
            switch (whatToDo){
                // data management operations was chosen
                case 1:
                    while(true){
                        int whatToWorkWith = InputReader.readInt("\nВиберіть, з чим хочете працювати: " +
                                "\n1 - факультет" +
                                "\n2 - кафедра" +
                                "\n3 - студент" +
                                "\n4 - викладач" +
                                "\n0 - повернутись на крок назад", 0, 4);
                        switch (whatToWorkWith){
                            // faculty was chosen
                            case 1:
                                FacultyMenu.selectOperation();
                                break;
                            // department was chosen
                            case 2:
                                DepartmentMenu.selectOperation();
                                break;
                            // student was chosen
                            case 3:
                                StudentMenu.selectOperation();
                                break;
                            // teacher was chosen
                            case 4:
                                TeacherMenu.selectOperation();
                                break;
                            // exit was chosen
                            case 0:
                                break;
                        }
                        if(whatToWorkWith==0) break;
                    }
                    break;
                // search was chosen
                case 2:
                    while(true){
                        int whatToFind = InputReader.readInt("\nВиберіть, кого хочете знайти: " +
                                "\n1 - студента" +
                                "\n2 - викладача" +
                                "\n0 - повернутись на крок назад", 0, 2);
                        switch (whatToFind){
                            //find student was chosen
                            case 1:
                                StudentMenu.find();
                                break;
                            // find teacher was chosen
                            case 2:
                                TeacherMenu.find();
                                break;
                            //exit was chosen
                            case 0:
                                break;
                        }
                        if(whatToFind==0) break;
                    }
                    break;
                // reports was chosen
                case 3:
                    while(true){
                        int whatToShow = InputReader.readInt("\nВиберіть, які звіти хочете побачити: " +
                                "\n1 - студентів" +
                                "\n2 - викладачів" +
                                "\n0 - повернутись на крок назад", 0, 2);
                        switch (whatToShow){
                            //show student was chosen
                            case 1:
                                StudentMenu.report();
                                break;
                            //show teacher was chosen
                            case 2:
                                TeacherMenu.report();
                                break;
                            //exit was chosen
                            case 0:
                                break;
                        }
                        if(whatToShow==0) break;
                    }
                    break;
                // exit was chosen
                case 0:
                    System.out.println("\nДякую, що прийшли!");
                    break;
            }
            if (whatToDo==0) break;
        }
    }

}
