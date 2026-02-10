package domain;

import repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Student extends Person {
    private static Scanner scanner = new Scanner(System.in);
    private String studentId;
    private int course;
    private String group;
    private int enrollmentYear;
    private EducationForm educationForm;  // enum
    private StudentStatus status; // enum
    private Department department;

    public Student(String id, String lastName, String firstName, String patronymic,
                   String birthDate, String email, String phone,
                   Department department, String studentId, int course, String group,
                   int enrollmentYear, EducationForm educationForm,
                   StudentStatus status) {
        super(id, lastName, firstName, patronymic, birthDate, email, phone);
        this.department = department;
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        this.enrollmentYear = enrollmentYear;
        this.educationForm = educationForm;
        this.status = status;
    }

    public String getStudentId() { return studentId; }
    public int getCourse() { return course; }
    public String getGroup() { return group; }
    public int getEnrollmentYear() { return enrollmentYear; }
    public EducationForm getEducationForm() { return educationForm; }
    public StudentStatus getStatus() { return status; }

    public void setCourse(int course) { this.course = course; }
    public void setGroup(String group) { this.group = group; }
    public void setStatus(StudentStatus status) { this.status = status; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setEnrollmentYear(int enrollmentYear) { this.enrollmentYear = enrollmentYear; }
    public void setEducationForm(EducationForm educationForm) { this.educationForm = educationForm; }

    public String getRole() {
        return "Студент";
    }

    @Override
    public String toString() {
        return String.format("Студент: %s, Кафедра: %s, ID студента: %s, Курс: %d, Група: %s, Рік вступу: %d, Форма навчання: %s, Статус: %s",
                        super.toString(),
                        (department != null ? department.getName() : "не призначено"),
                        studentId, course, group, enrollmentYear,
                        educationForm.getDisplayName(),
                        status.getDisplayName());
    }
    public static void selectStudentOperation() {
        while (true) {
            System.out.println("\nУПРАВЛІННЯ СТУДЕНТАМИ");
            System.out.println("1 - Створити нового студента");
            System.out.println("2 - Показати всіх студентів");
            System.out.println("3 - Редагувати існуючого");
            System.out.println("4 - Видалити існуючого");
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
                    createStudent();
                    break;
                case 2:
                    System.out.println("Список всіх студентів:");
                    Repository.getInstance().showAllStudents();
                    break;
                case 3:
                    Repository.getInstance().changeStudent();
                    break;
                case 4:
                    Repository.getInstance().deleteStudent();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Немає такої опції, спробуйте ще раз.");
                    break;
            }
        }
    }

    private static void createStudent() {
        System.out.println("Введіть 1, щоби розпочати створення студента, або 0, щоби повернутися назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        while (makingSure != 0 && makingSure != 1) {
            System.out.println("Немає такої опції, введіть 1 або 0: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
        }
        if (makingSure == 1) {
            Department dep = departmentValidation();
            Repository.getInstance().addStudents(new Student(
                    idValidationForStudents(),
                    Person.lastNameValidation(),
                    Person.firstNameValidation(),
                    Person.patronymicValidation(),
                    Person.birthDateValidation(),
                    Person.emailValidation(),
                    Person.phoneNumberValidation(),
                    dep,
                    studentIdValidation(),
                    courseValidation(),
                    groupValidation(),
                    enrollmentYearValidation(),
                    educationFormValidation(),
                    statusValidation()));

            System.out.println("Ви успішно додали студента: \n" + Repository.getInstance().lastAddedStudent());
        }

    }
    public static Department departmentValidation(){
        while (true) {
            System.out.println("Виберіть кафедру студента: " +
                    "\n1 - вибрати з існуючих" +
                    "\n2 - створити нову кафедру");

            int choice;
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Введіть 1 або 2:");
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                Repository.getInstance().showAllDepartments();
                if (!Repository.getInstance().getDepartments().isEmpty()) {
                    System.out.println("Введіть унікальний код кафедри:");
                    String code = scanner.nextLine();
                    Optional<Department> maybeDep = Repository.getInstance().findDepartmentByUniqueCode(code);
                    if (maybeDep.isPresent()) {
                        return maybeDep.get();
                    } else {
                        System.out.println("Кафедру з таким кодом не знайдено!");
                    }
                } else {
                    System.out.println("Кафедр ще немає. Створіть нову (опція 2).");
                }
            } else if (choice == 2) {
                Department.createDepartment();
                return Repository.getInstance().lastAddedDepartment();
            }
        }
    }
    public static StudentStatus statusValidation() {
        System.out.println("Виберіть статус студента: " +
                "\n1 - Навчається " +
                "\n2 - Академвідпустка " +
                "\n3 - Відрахований");
        int choice;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число (1, 2 або 3): ");
        }
        choice = scanner.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Немає такої опції, введіть число від 1 до 3: ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Немає такої опції, введіть число: ");
            }
            choice = scanner.nextInt();
        }
        scanner.nextLine();
        if (choice == 1) return StudentStatus.STUDYING;
        if (choice == 2) return StudentStatus.ACADEMIC_LEAVE;
        return StudentStatus.EXPELLED;
    }

    public static EducationForm educationFormValidation() {
        System.out.println("Виберіть форму навчання: " +
                "\n1 - Бюджет " +
                "\n2 - Контракт");
        int choice;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число (1 або 2): ");
        }
        choice = scanner.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.println("Немає такої опції, введіть число (1 або 2): ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Немає такої опції, введіть число: ");
            }
            choice = scanner.nextInt();
        }
        scanner.nextLine();
        if (choice == 1) {
            return EducationForm.BUDGET;
        } else {
            return EducationForm.CONTRACT;
        }
    }

    public static int enrollmentYearValidation() {
        int enYear;
        System.out.println("Введіть рік вступу: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Помилка! Введіть правильний рік вступу цифрами:");
        }
        enYear = scanner.nextInt();
        while (enYear < 2018 || enYear > 2026) {
            System.out.println("Рік вступу має бути в межах від 2020 до 2025! ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Помилка! Введіть правильний рік вступу: ");
            }
            enYear = scanner.nextInt();
        }
        return enYear;
    }

    public static String groupValidation() {
        scanner.nextLine();
        System.out.println("Введіть назву групи: ");
        String group = scanner.nextLine();
        while (group.isEmpty() || group.length() > 7){
            if (group.isEmpty()) {
                System.out.println("Назва групи не може бути порожньою, введіть ще раз: ");
            } else {
                System.out.println("Назва групи занадто довга (максимально 7 знаків), введіть коротшу: ");
            }
            group = scanner.nextLine();
            }
        return group;
        }


    public static int courseValidation() {
        System.out.println("Введіть курс студента (1-6): ");
        int course;

        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Не вірно! Введіть курс студента цифрами ");
        }
        course = scanner.nextInt();

        while (course < 1 || course > 6) {
            System.out.println("Не вірно! Введіть курс студента (від 1 до 6): ");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Не вірно! Введіть номер курсу(від 1 до 6): ");
            }
            course = scanner.nextInt();
        }
        return course;
    }

    private static String studentIdValidation() {
        System.out.println("Введіть номер залікової книжки (8 знаків): ");
        String sId = scanner.nextLine();
        while (sId.length() != 8) {
            System.out.println("Невірна довжина! Номер залікової книжки має містити 8 знаків:");
            sId = scanner.nextLine();
        }
        List<Student> students = Repository.getInstance().getStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(sId)) {
                System.out.println("Студент з таким номером залікової книжки вже існує! Введіть інший номер:");
                sId = scanner.nextLine();
                while (sId.length() != 8) {
                    System.out.println("Має бути 8 знаків:");
                    sId = scanner.nextLine();
                }
            }
        }
        return sId;
    }

    private static String idValidationForStudents() {
        scanner.nextLine();
        System.out.println("Введіть унікальний ідентифікатор з 5 знаків: ");
        String id = scanner.nextLine();
        while(id.length() != 5){
            System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
            id = scanner.nextLine();
        }
        List<Student> students = Repository.getInstance().getStudents();
        if (students.size() != 0) {
            for (int i = 0; i < students.size(); i++) {
                while (students.get(i).getId().equals(id)) {
                    System.out.println("Студент з таким id уже існує, введіть інший:");
                    id = scanner.nextLine();
                    while (id.length() != 5) {
                        System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                        id = scanner.nextLine();
                    }
                }
            }
        }
        return id;
    }

}



    

