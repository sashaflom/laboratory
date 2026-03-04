package ui;

import domain.*;
import services.DepartmentService;
import services.FacultyService;
import services.StudentService;
import services.TeacherService;
import validators.InputReader;

import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        University kyivMohylaAcademy = new University("Національний університет 'Києво-Могилянська академія'",
                "НаУКМА", "Київ", "вулиця Григорія Сковороди, 2, Київ, 04655");
        System.out.println("Вітаю в інформаційній системі Києво-Могилянської академії!");
        System.out.println("\nСтворено:" + kyivMohylaAcademy);
        // test
        Teacher dean = new Teacher("11111", "Глибовець", "Андрій",
                "Миколайович", "9.7.1980", "a.hlybovets@ukma.edu.ua",
                "+380955941312", null, Position.DEAN, AcademicDegree.DOCTOR,
                AcademicTitle.PROFESSOR, "3.12.2015", 10000);
        Teacher head = new Teacher("22222", "Вознюк", "Ярослав",
                "Іванович", "9.7.1980", "y.vozniuk@ukma.edu.ua",
                "+380955941312", null, Position.HEAD, AcademicDegree.DOCTOR,
                AcademicTitle.PROFESSOR, "3.12.2015", 10000);
        TeacherService.createNewAndAdd("11111", "Глибовець", "Андрій",
                "Миколайович", "9.7.1980", "a.hlybovets@ukma.edu.ua",
                "+380955941312", null, Position.DEAN, AcademicDegree.DOCTOR,
                AcademicTitle.PROFESSOR, "3.12.2015", 10000);
        TeacherService.createNewAndAdd("22222", "Вознюк", "Ярослав",
                "Іванович", "9.7.1980", "y.vozniuk@ukma.edu.ua",
                "+380955941312", null, Position.HEAD, AcademicDegree.DOCTOR,
                AcademicTitle.PROFESSOR, "3.12.2015", 10000);
        Faculty faculty = new Faculty("1111111", "Факультет інформатики",
                "ФІ", dean, "1-201");
        FacultyService.createNewAndAdd("1111111", "Факультет інформатики",
                "ФІ", dean, "1-201");
        DepartmentService.createNewAndAdd("1111", "Кафедра інформатики",
                faculty, head, "1-301");
        Department department = new Department("1111", "Кафедра інформатики",
                faculty, head, "1-301");
        StudentService.createNewAndAdd("11111", "Фломбойм", "Олександра",
                "Олексіївна", "29.07.2008", "o.flomboim@ukma.edu.ua",
                "+380955941312", department, "11111111", 1, 2,
                2025, EducationForm.BUDGET, StudentStatus.STUDYING);
        StudentService.createNewAndAdd("22222", "Фломбойм", "Олександра",
                "Олексіївна", "29.07.2008", "o.flomboim@ukma.edu.ua",
                "+380955941312", department, "11111111", 1, 2,
                2025, EducationForm.BUDGET, StudentStatus.STUDYING);
        StudentService.createNewAndAdd("33333", "Фломбойм", "Олександра",
                "Олексіївна", "29.07.2008", "o.flomboim@ukma.edu.ua",
                "+380955941312", department, "11111111", 1, 2,
                2025, EducationForm.BUDGET, StudentStatus.STUDYING);

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
