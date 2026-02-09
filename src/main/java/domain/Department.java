/*
Have to finish:
- createDepartment() -validation: facultyValidation(),
                      headOfDepartmentValidation(),
                      locationValidation()
*/
package domain;

import repository.Repository;

import java.util.List;
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
            }
        }
    }

    private static void createDepartment() {
        System.out.println("Введіть 1, щоби розпочати створення кафедри, або 0, щоби повернутися назад: ");
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
       /* if (makingSure == 1) {
            scanner.nextLine();
            Repository.getInstance().addDepartment(new Department(
                    uniqueCodeValidation(),
                    departmentNameValidation(),
                    facultyValidation(),
                    headOfDepartmentValidation(),
                    locationValidation()
            ));

            System.out.println("Ви успішно додали кафедру: \n" + Repository.getInstance().lastAddedDepartment());
        }
        */
    }

    /*private static String locationValidation() {
    }

    private static Teacher headOfDepartmentValidation() {
    }

    private static Faculty facultyValidation() {
    }
    */

    private static String departmentNameValidation() {
        System.out.println("Введіть назву кафедри: ");
        String name = scanner.nextLine();
        while (name.isEmpty()||name.length() > 20) {
            System.out.println("Назва не може бути порожньою або довшою за 20 символів! Введіть назву: ");
            name = scanner.nextLine();
        }
        return name;
    }

    private static String uniqueCodeValidation() {
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