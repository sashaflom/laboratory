package domain;

import repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Department {

    private static Scanner scanner = new Scanner(System.in);
    private final String uniqueCode;
    private String name;
    private Faculty faculty;
    private Teacher headOfDepartment;
    private String location;

    public Department(String uniqueCode, String name, Faculty faculty, Teacher headOfDepartment, String location){
        this.uniqueCode = uniqueCode;
        this.name = name;
        this.faculty = faculty;
        this.headOfDepartment = headOfDepartment;
        this.location = location;
    }

    @Override
    public String toString(){
        return "\nКафедра: \nунікальний код: '%s', \nназва: '%s', \nфакультет: '%s', \nзавідувач: '%s', \nлокація: '%s'.".formatted(uniqueCode, name, faculty, headOfDepartment, location);
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Teacher getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Teacher headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static void selectDepartmentOperation() {
        while (true) {
            System.out.println("\nУПРАВЛІННЯ КАФЕДРАМИ");
            System.out.println("1 - Створити нову кафедру");
            System.out.println("2 - Показати всі кафедри");
            System.out.println("3 - Редагувати існуючу кафедру");
            System.out.println("4 - Видалити існуючу кафедру");
            System.out.println("0 - Повернутися в головне меню");
            System.out.print("Ваш вибір: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Помилка: введіть число від 0 до 4!");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createDepartment();
                    break;
                case 2:
                    System.out.println("\nСПИСОК ВСІХ КАФЕДР:");
                    if (Repository.getInstance().getDepartments().isEmpty()) {
                        System.out.println("Кафедр поки що не створено.");
                    } else {
                        Repository.getInstance().showAllDepartments();
                    }
                    break;
                case 3:
                    Repository.getInstance().changeDepartment();
                    break;
                case 4:
                    Repository.getInstance().deleteDepartment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Помилка: опції '" + choice + "' не існує. Спробуйте ще раз.");
                    break;
            }
        }
    }

   public static void createDepartment() {
        System.out.println("Введіть 1, щоб розпочати створення кафедри, або 0, щоб повернутися назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        scanner.nextLine();
        while (makingSure != 0 && makingSure != 1) {
            System.out.println("Немає такої опції, введіть 1 або 0: ");
            if (scanner.hasNextInt()) {
                makingSure = scanner.nextInt();
            } else {
                scanner.next();
            }
            scanner.nextLine();
        }

        if (makingSure == 1) {
            scanner.nextLine();
            Faculty selectedFaculty = facultyValidation();
            if (selectedFaculty == null) {
                System.out.println("Додавання кафедри скасовано.");
                return;
            }

            Repository.getInstance().addDepartment(new Department(
                    uniqueCodeValidation(),
                    departmentNameValidation(),
                    selectedFaculty,
                    headOfDepartmentValidation(),
                    locationValidation()
            ));

            System.out.println("Ви успішно додали кафедру: \n" + Repository.getInstance().lastAddedDepartment());
        }

    }

    public static String locationValidation() {
        System.out.println("Введіть локацію кафедри (номер корпусу чи аудиторії): ");
        String location = scanner.nextLine();
        while (location.isEmpty() || location.length() > 50) {
            System.out.println("Локація не може бути порожньою або довшою за 50 символів! Спробуйте ще раз:");
            location = scanner.nextLine();
        }
        return location;
    }

    public static Teacher headOfDepartmentValidation() {
        while (true) {
            System.out.println("Виберіть, як хочете призначити завідувача кафедри: " +
                    "\n1 - Вибрати з уже існуючих викладачів" +
                    "\n2 - Створити нового викладача та призначити завідувачем");
            int choice;
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції(1 чи 2): ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            while (choice != 1 && choice != 2) {
                System.out.println("Немає такої опції, введіть 1 або 2: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next();
                }
                scanner.nextLine();
            }
            if (choice == 1) {
                if (Repository.getInstance().getTeachers().isEmpty()) {
                    System.out.println("Список викладачів порожній! Створіть нового викладача (оберіть опцію 2).");
                    continue;
                }
                System.out.println("СПИСОК НАЯВНИХ ВИКЛАДАЧІВ: ");
                Repository.getInstance().showAllTeachers();
                System.out.println("Введіть ID викладача, якого хочете призначити завідувачем: ");
                String id = scanner.nextLine();
                while (id.length() != 5) {
                    System.out.println("ID має містити рівно 5 знаків! Спробуйте ще раз: ");
                    id = scanner.nextLine();
                }
                Optional<Teacher> maybeTeacher = Repository.getInstance().findTeacherByID(id);
                if (maybeTeacher.isPresent()) {
                    System.out.println("Викладача "+ maybeTeacher.get().getFullName()+ " призначено завідувачем" );//ADD THE NAME
                    return maybeTeacher.get();
                }else{
                    System.out.println("Викладача з таким ID не знайдено.");
                }
            }
            if (choice == 2) {
                Teacher.createNew();
                Teacher lastTeacher = Repository.getInstance().findLastAddedTeacher();
                System.out.println("Нового викладача " + lastTeacher.getFullName() + " призначено завідувачем.");
                return lastTeacher;
            }
        }
    }

    public static Faculty facultyValidation() {
        System.out.println("Вибір факультету, для створення кафедри");
        while (true) {
            System.out.println("Введіть унікальний код факультету, до якого належить кафедра: ");
            String code = scanner.nextLine();
            java.util.Optional<Faculty> foundF = Repository.getInstance().findFacultyByUniqueCode(code);
            if (foundF.isPresent()) {
                return foundF.get();
            }
            System.out.println("Факультету з кодом '" + code + "' не існує в системі.");
            System.out.println("Введіть 1, щоби спробувати ще раз, або 0, щоби повернутися до меню: ");
            int makingSure;
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
            scanner.nextLine();
            while (makingSure != 0 && makingSure != 1) {
                System.out.println("Немає такої опції, введіть 1 або 0: ");
                if (scanner.hasNextInt()) {
                    makingSure = scanner.nextInt();
                } else {
                    scanner.next();
                }
                scanner.nextLine();
            }
            if (makingSure == 0) {
                return null;
            }
        }
    }

    public static String departmentNameValidation() {
        System.out.println("Введіть назву кафедри: ");
        String name = scanner.nextLine();
        while (name.isEmpty()||name.length() > 20) {
            System.out.println("Назва не може бути порожньою або довшою за 20 символів! Введіть назву: ");
            name = scanner.nextLine();
        }
        return name;
    }

    public static String uniqueCodeValidation() {
        System.out.println("Введіть унікальний код кафедри: ");
        String code = scanner.nextLine();
        while (code.isEmpty() || code.length() > 4) {
            System.out.println("Код не може бути порожнім або довшим за 4 символи. Спробуйте ще раз:");
            code = scanner.nextLine();
        }
        List<Department> dep = Repository.getInstance().getDepartments();
        for (int i = 0; i < dep.size(); i++) {
            if (dep.get(i).getUniqueCode().equalsIgnoreCase(code)) {
                System.out.println("Кафедра з таким кодом вже існує! Введіть інший: ");
                code = scanner.nextLine();
            }
        }
        return code;
    }
}