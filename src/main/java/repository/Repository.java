package repository;

import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Repository {

    private Scanner scanner = new Scanner(System.in);

    // static class variable that will store a reference to a single instance of the class
    private static Repository instance = null;

    private List<Faculty> faculties = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private Repository(){

    }

    // a static class method that should return a reference to a single instance of the class
    public static Repository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new Repository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public Teacher findLastAddedTeacher(){
        return teachers.get(teachers.size()-1);
    }

    public void deleteTeacher(){
        System.out.println("Введіть 1, щоби розпочати, або 0, щоби повернутися на крок назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        while(makingSure!=0 && makingSure!=1){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
        }
        if(makingSure==1){
            System.out.println("Список наявних викладачів:");
            showAllTeachers();
            if(teachers.size()!=0){
                System.out.println("Введіть ID викладача, якого хочете видалити: ");
                scanner.nextLine();
                String id = scanner.nextLine();
                while(id.length() != 5){
                    System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                    id = scanner.nextLine();
                }
                Optional<Teacher> maybeTeacher = findTeacherByID(id);
                if (maybeTeacher.isPresent()){
                    Teacher foundTeacher = maybeTeacher.get();
                    teachers.remove(foundTeacher);
                    System.out.println("Ви успішно видалили викладача. Оновлений список наявних викладачів:");
                    showAllTeachers();
                }else{
                    System.out.println("Немає викладача з таким ID.");
                }
            }else{
                System.out.println("Не створено жодного викладача, отже ви не можете нікого видалити");
            }

        }
    }

    public void showAllTeachers(){
        if (teachers.size()!=0){
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }else{
            System.out.println("Поки що немає жодного викладача");
        }
    }

    public Optional<Teacher> findTeacherByID(String id){
        Optional<Teacher> foundTeacher = Optional.empty();
        if(teachers.size()!=0){
            for (Teacher teacher : teachers) {
                if(teacher.getId().equals(id)){
                    foundTeacher = Optional.of(teacher);
                    break;
                }
            }
        }else{
            System.out.println("Не створено жодного викладача, отже ви не можете знайти");
        }

        return foundTeacher;
    }

    public void findTeacherByFullName(String fullName){
        boolean ifWasFound = false;
        if(teachers.size()!=0){
            for(Teacher teacher : teachers){
                if(teacher.getFullName().equals(fullName)){
                   System.out.println(teacher);
                   ifWasFound = true;
                }
            }
            if(!ifWasFound){
                System.out.println("Немає такого викладача.");
            }
        }else{
            System.out.println("Немає жодного створеного викладача");
        }
    }

    public void changeTeacher(){
        System.out.println("Введіть 1, щоби розпочати, або 0, щоби повернутися на крок назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        while(makingSure!=0 && makingSure!=1){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
        }
        if(makingSure==1) {
            System.out.println("Список наявних викладачів:");
            showAllTeachers();
            if(teachers.size()!=0){
                System.out.println("Введіть ID викладача, якого хочете редагувати: ");
                scanner.nextLine();
                String id = scanner.nextLine();
                while (id.length() != 5) {
                    System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                    id = scanner.nextLine();
                }
                Optional<Teacher> maybeTeacher = findTeacherByID(id);
                if (maybeTeacher.isPresent()){
                    Teacher foundTeacher = maybeTeacher.get();
                    while(true){
                        System.out.println("Виберіть, що ви хочете змінити:" +
                                "\n1 - прізвище" +
                                "\n2 - ім'я" +
                                "\n3 - побатькові" +
                                "\n4 - дату народження" +
                                "\n5 - email" +
                                "\n6 - номер телефону" +
                                "\n7 - посаду" +
                                "\n8 - науковий ступінь" +
                                "\n9 - вчене звання" +
                                "\n10 - дату прийняття на роботу" +
                                "\n11 - ставку" +
                                "\n0 - вийти на рівень вище");
                        int whatToChange;
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                        }
                        whatToChange = scanner.nextInt();
                        while(whatToChange<0 || whatToChange>11){
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                            while (!scanner.hasNextInt()) {
                                String input = scanner.next();
                                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                            }
                            whatToChange = scanner.nextInt();
                        }

                        // last name to change
                        if (whatToChange==1){
                            foundTeacher.setLastname(Person.lastNameValidation());
                            // first name to change
                        }else if(whatToChange==2){
                            foundTeacher.setFirstname(Person.firstNameValidation());
                            // patronymic name to change
                        }else if (whatToChange==3){
                            foundTeacher.setPatronymic(Person.patronymicValidation());
                            // birthdate to change
                        }else if(whatToChange==4){
                            foundTeacher.setBirthDate(Person.birthDateValidation());
                            // email to change
                        }else if(whatToChange==5){
                            foundTeacher.setEmail(Person.emailValidation());
                            // phone number to change
                        }else if (whatToChange==6){
                            foundTeacher.setPhoneNumber(Person.phoneNumberValidation());
                            // position to change
                        }else if(whatToChange==7){
                            foundTeacher.setPosition(Teacher.positionValidation());
                            // academic degree was chosen
                        }else if(whatToChange==8){
                            foundTeacher.setAcademicDegree(Teacher.academicDegreeValidation());
                            // academic title to change
                        }else if (whatToChange==9){
                            foundTeacher.setAcademicTitle(Teacher.academicTitleValidation());
                            // hire date to change
                        }else if(whatToChange==10){
                            foundTeacher.setHireDate(Teacher.hireDateValidation());
                            // workload to change
                        }else if(whatToChange==11){
                            foundTeacher.setWorkload(Teacher.workloadValidation());
                            // exit
                        }else if(whatToChange==0){
                            break;
                        }
                        System.out.println("Ви успішно змінили викладача: " + foundTeacher);
                    }
                }else{
                    System.out.println("Немає викладача з таким ID.");
                }
            }else{
                System.out.println("Не створено жодного викладача, отже ви не можете змінити");
            }

        }
    }

    public void addStudents(Student student){ students.add(student); }

    public void showAllStudents(){
        System.out.println("\n СПИСОК ВСІХ СТУДЕНТІВ:");
        if(students.isEmpty()){
            System.out.println("Не знайдено жодного студента");
            return;
        }
        int count = 1; // Counter to display student numbers in the list
        for(Student student : students){
            System.out.print(count + ". ");
            System.out.println(student);
            count++;
        }
    }

    public Optional <Student> findStudentByID(String id) {
        Optional<Student> foundStudent = Optional.empty();
        if (students.isEmpty()) {
            System.out.println("Список студентів порожній. Пошук неможливий.");
            return foundStudent;
        }
        for (Student student : students) {
            if (student.getId().equals(id)) {
                foundStudent = Optional.of(student);
                break;
            }
        }
        if (foundStudent.isEmpty()) {
            System.out.println("Студента з ID " + id + " не знайдено");
        }
        return foundStudent;
    }

    public void deleteStudent(){
        System.out.println("Введіть 1, щоб видалити студента. Введіть 0 щоб повернутись назад");
        int makingSure;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число 1 або 0:");
        }
        makingSure = scanner.nextInt();
            scanner.nextLine();
            if (makingSure == 0) return;
            if(students.isEmpty()){
                System.out.println("Список студентів порожній");
                return;
            }
        System.out.println("Список наявних студентів:");
        showAllStudents();

        System.out.println("Введіть ID студента, якого хочете видалити :");
        String id = scanner.nextLine();
        while(id.length()!=5){
            System.out.println("Некоректний ID, введіть 5 знаків:");
            id = scanner.nextLine();
        }
        Optional<Student> maybeStudent = findStudentByID(id);
        if(maybeStudent.isPresent()){
            Student foundStudent = maybeStudent.get();
            students.remove(foundStudent);
            System.out.println("Ви успішно видалили студента");
            showAllStudents();
        }else{
            System.out.println("Студента з таким ID не знайдено.");
        }
    }

    public Student lastAddedStudent(){
        return students.get(students.size()-1);
    }

    public void changeStudent(){
        System.out.println("Введіть 1, щоб розпочати редагування, або 0, щоб повернутися назад:");
        int makingSure;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число 0 або 1");
        }
        makingSure = scanner.nextInt();
        if (makingSure == 0) return;
        if(students.isEmpty()){
            System.out.println("Список студентів порожній, нічого редагувати.");
            return;
        }
        System.out.println("Список наявних студентів:");
        showAllStudents();

        System.out.println("Введіть Id студента, якого хочете редагувати: ");
        scanner.nextLine();
        String id = scanner.nextLine();
        Optional<Student> maybeStudent = findStudentByID(id);
        if (maybeStudent.isPresent()) {
            Student foundStudent = maybeStudent.get();
            while (true) {
                System.out.println("\nВиберіть, що ви хочете змінити у " + foundStudent.getLastname() + ":");
                System.out.println("1 - Прізвище\n2 - Ім'я\n3 - По батькові\n4 - Дату народження\n5 - Email\n6 - Телефон");
                System.out.println("7 - Курс\n8 - Групу\n9 - Рік вступу\n10 - Форму навчання\n11 - Статус");
                System.out.println("0 - Повернутися назад");
                int whatToChange;
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Введіть число!");
                }
                whatToChange = scanner.nextInt();
                scanner.nextLine();
                if (whatToChange == 0) break;
                switch (whatToChange) {
                    case 1:
                        foundStudent.setLastname(Person.lastNameValidation());
                        break;
                    case 2:
                        foundStudent.setFirstname(Person.firstNameValidation());
                        break;
                    case 3:
                        foundStudent.setPatronymic(Person.patronymicValidation());
                        break;
                    case 4:
                        foundStudent.setBirthDate(Person.birthDateValidation());
                        break;
                    case 5:
                        foundStudent.setEmail(Person.emailValidation());
                        break;
                    case 6:
                        foundStudent.setPhoneNumber(Person.phoneNumberValidation());
                        break;
                    case 7:
                        foundStudent.setCourse(Student.courseValidation());
                        break;
                    case 8:
                        foundStudent.setGroup(Student.groupValidation());
                        break;
                    case 9:
                        foundStudent.setEnrollmentYear(Student.enrollmentYearValidation());
                        break;
                    case 10:
                        foundStudent.setEducationForm(Student.educationFormValidation());
                        break;
                    case 11:
                       foundStudent.setStatus(Student.statusValidation());
                        break;
                    default:
                        System.out.println("Невірна опція.");
                }
                System.out.println("Дані оновлено: " + foundStudent);
            }
        } else {
            System.out.println("Студента з таким ID не знайдено.");
        }
    }

    public void findStudent() {
        if (students.isEmpty()) {
            System.out.println("Список студентів порожній.");
            return;
        }
        System.out.println("\nОберіть критерій пошуку:");
        System.out.println("1 - За ПІБ");
        System.out.println("2 - За курсом");
        System.out.println("3 - За групою");
        System.out.println("0 - Скасувати");
        int choice;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Введіть число від 0 до 3:");
        }
        choice = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;
        switch (choice) {
            case 1:
                scanner.nextLine();
                System.out.println("Введіть ПІБ студента:");
                String nameInput = scanner.nextLine().toLowerCase();
                System.out.println("\nРезультати пошуку:"); // Заголовок
                for (Student s : students) {
                    if (s.getFullName().toLowerCase().equals(nameInput)) {
                        System.out.println(s);
                        found = true;
                    }
                }
                break;
            case 2:
                int searchCourse = Student.courseValidation();
                System.out.println("\nСписок студентів " + searchCourse + "-го курсу:");
                for (Student s : students) {
                    if (s.getCourse() == searchCourse) {
                        System.out.println(s);
                        found = true;
                    }
                }
                break;
            case 3:
                String sg = Student.groupValidation();
                System.out.println("\nСписок студентів групи " + sg + ":");
                for (Student s : students) {
                    if (s.getGroup().equalsIgnoreCase(sg)) {
                        System.out.println(s);
                        found = true;
                    }
                }
                break;
            case 0:
                return;
            default:
                System.out.println("Невірна опція.");
                return;
        }
        if (!found && choice != 0) {
            System.out.println("За вашим запитом нічого не знайдено.");
        }
    }


    
    public void addDepartment(Department department) {departments.add(department);}

    public void deleteDepartment() {
        System.out.println("Введіть 1, щоб видалити кафедру. Введіть 0 щоб повернутись назад");
        int makingSure;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число 1 або 0:");
        }
        makingSure = scanner.nextInt();
        scanner.nextLine();
        if (makingSure == 0) return;
        if(departments.isEmpty()){
            System.out.println("Список кафедр порожній");
            return;
        }
        System.out.println("Список кафедр університету:");
        showAllDepartments();

        System.out.println("Введіть унікальний код кафедри,яку хочете видалити:");
        String uniqueCode = scanner.nextLine();
        while(uniqueCode.length()!=4){
            System.out.println("Некоректний унікальний код, введіть 4 знаків:");
            uniqueCode = scanner.nextLine();
        }
        Optional<Department> maybeDepartment = findDepartmentByUniqueCode(uniqueCode);
        if(maybeDepartment.isPresent()){
            Department foundDepartment =maybeDepartment.get();
            departments.remove(foundDepartment);
            System.out.println("Ви успішно видалили кафедру");
            showAllDepartments();
        }else{
            System.out.println("Кафедру за таким унікальним кодом не знайдено.");
        }
    }

    public Optional<Department> findDepartmentByUniqueCode(String uniqueCode ){
        Optional<Department> foundDepartament = Optional.empty();
        if (departments.size()!=0) {
            for (Department department : departments) {
                if (department.getUniqueCode().equals(uniqueCode)) {
                    foundDepartament = Optional.of(department);
                    break;
                }
            }
        }else {
            System.out.println("Не створено жодної кафедри, отже пошук неможливий.");
        }
        if (foundDepartament.isEmpty()) {
            System.out.println("Кафедру з кодом " + uniqueCode + " не знайдено.");
        }
        return foundDepartament;
    }

    public Department lastAddedDepartment() {
        if (departments.isEmpty()) {
            System.out.println("Список кафедр порожній!");
            return null;
        }
        return departments.get(departments.size() - 1);
    }

    public void showAllDepartments() {
        System.out.println("\n СПИСОК ВСІХ КАФЕДР:");
        if(students.isEmpty()){
            System.out.println("Не знайдено жодного кафедри");
            return;
        }
        int count = 1;
        for(Department department : departments){
            System.out.print(count + ". ");
            System.out.println(department);
            count++;
        }
    }

    public void changeDepartment() {
        System.out.println("Введіть 1, щоб розпочати редагування, або 0, щоб повернутися назад:");
        int makingSure;
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Немає такої опції, введіть число 0 або 1");
        }
        makingSure = scanner.nextInt();
        if (makingSure == 0) return;
        if(departments.isEmpty()){
            System.out.println("Список кафедр порожній. Редагування неможливе");
            return;
        }
        System.out.println("Список наявних кафедр:");
        showAllDepartments();

        System.out.println("Введіть унікальний код кафедри, яку хочете редагувати: ");
        scanner.nextLine();
        String uniqueCode = scanner.nextLine();
        Optional<Department> maybeDepartment= findDepartmentByUniqueCode(uniqueCode);
        if (maybeDepartment.isPresent()) {
            Department dep = maybeDepartment.get();
            System.out.println("Редагування кафедри: " + dep.getName());
            System.out.println("1 - Змінити назву");
            System.out.println("2 - Змінити факультет");
            System.out.println("3 - Змінити завідувача");
            System.out.println("4 - Змінити локацію");
            System.out.println("0 - Завершити редагування");
            System.out.print("Ваш вибір: ");

            int editChoice;
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Введіть число від 0 до 4:");
            }
            editChoice = scanner.nextInt();
            scanner.nextLine();
            switch (editChoice) {
                case 1:
                    maybeDepartment.get().setName(Department.departmentNameValidation());
                    break;
                case 2:
                    Faculty newFaculty = Department.facultyValidation();
                    if (newFaculty != null) {
                        maybeDepartment.get().setFaculty(newFaculty);
                    }
                    break;
                case 3:
                    Teacher newHead = Department.headOfDepartmentValidation();
                    if (newHead != null) {
                        maybeDepartment.get().setHeadOfDepartment(newHead);
                    }
                    break;
                case 4:
                    maybeDepartment.get().setLocation(Department.locationValidation());
                    break;
                default:
                    System.out.println("Невірна опція.");
                   break;
            }
            System.out.println("Дані оновлено: " + maybeDepartment.get());

        }

    }

    public void showAllFaculties(){
        if (faculties.size()!=0){
            for (Faculty faculty : faculties) {
                System.out.println(faculty);
            }
        }else{
            System.out.println("Поки що немає жодного факультету");
        }
    }

    public void changeFaculty(){
        System.out.println("Введіть 1, щоби розпочати, або 0, щоби повернутися на крок назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        while(makingSure!=0 && makingSure!=1){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
        }

        if(makingSure==1){
            System.out.println("СПИСОК НАЯВНИХ ФАКУЛЬТЕТІВ:");
            showAllFaculties();
            if(faculties.size()!=0){
                System.out.println("Введіть унікальний код факультету, який хочете редагувати: ");
                scanner.nextLine();
                String uniqueCode = scanner.nextLine();
                while (uniqueCode.length() != 7) {
                    System.out.println("Не може бути такої довжини, введіть 7 знаків: ");
                    uniqueCode = scanner.nextLine();
                }
                Optional<Faculty> maybeFaculty = findFacultyByUniqueCode(uniqueCode);
                if (maybeFaculty.isPresent()){
                    Faculty foundFaculty = maybeFaculty.get();
                    while(true){
                        System.out.println("Виберіть, що ви хочете змінити:" +
                                "\n1 - повну назву" +
                                "\n2 - скорочену назву" +
                                "\n3 - декана" +
                                "\n4 - контакти" +
                                "\n0 - вийти на рівень вище");
                        int whatToChange;
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                        }
                        whatToChange = scanner.nextInt();
                        while(whatToChange<0 || whatToChange>4){
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                            while (!scanner.hasNextInt()) {
                                String input = scanner.next();
                                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                            }
                            whatToChange = scanner.nextInt();
                        }

                        // full name to change
                        if (whatToChange==1){
                            foundFaculty.setFullName(Faculty.fullNameValidation());
                            // short name to change
                        }else if(whatToChange==2){
                            foundFaculty.setShortName(Faculty.shortNameValidation());
                            // dean to change
                        }else if (whatToChange==3){
                            foundFaculty.setDean(Faculty.deanValidation());
                            // contacts for communicaiton to change
                        }else if (whatToChange==4){
                            foundFaculty.setContactForCommunication(Faculty.contactForCommunicationValidation());
                            // exit
                        }else if(whatToChange==0){
                            break;
                        }
                        System.out.println("Ви успішно змінили факультет: " + foundFaculty);
                    }
                }else{
                    System.out.println("Немає факультету з таким унікальним кодом.");
                }
            }else{
                System.out.println("Не створено жодного факультету, отже ви не можете змінити");
            }
        }
    }

    public void deleteFaculty(){
        System.out.println("Введіть 1, щоби розпочати, або 0, щоби повернутися на крок назад: ");
        int makingSure;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
        }
        makingSure = scanner.nextInt();
        while(makingSure!=0 && makingSure!=1){
            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            makingSure = scanner.nextInt();
        }
        if(makingSure==1){
            System.out.println("СПИСОК НАЯВНИХ ФАКУЛЬТЕТІВ:");
            showAllFaculties();
            if(faculties.size()!=0){
                System.out.println("Введіть унікальний код факультета, який хочете видалити: ");
                scanner.nextLine();
                String uniqueCode = scanner.nextLine();
                while(uniqueCode.length() != 7){
                    System.out.println("Не може бути такої довжини, введіть 7 знаків: ");
                    uniqueCode = scanner.nextLine();
                }
                Optional<Faculty> maybeFaculty = findFacultyByUniqueCode(uniqueCode);
                if (maybeFaculty.isPresent()){
                    Faculty foundFaculty = maybeFaculty.get();
                    faculties.remove(foundFaculty);
                    System.out.println("Ви успішно видалили факультет. Оновлений список наявних факультетів:");
                    showAllFaculties();
                }else{
                    System.out.println("Немає факультету з таким ID.");
                }
            }else{
                System.out.println("Не створено жодного факультету, отже ви не можете нічого видалити");
            }

        }
    }

    public void addFaculty(Faculty faculty){
        faculties.add(faculty);
    }

    public Faculty findLastAddedFaculty(){
        return faculties.get(faculties.size()-1);
    }

    public Optional<Faculty> findFacultyByUniqueCode(String uniqueCode){
        Optional<Faculty> foundFaculty = Optional.empty();
        if(faculties.size()!=0){
            for (Faculty faculty : faculties) {
                if(faculty.getUniqueCode().equals(uniqueCode)){
                    foundFaculty = Optional.of(faculty);
                    break;
                }
            }
        }else{
            System.out.println("Не створено жодного факультету, отже ви не можете знайти");
        }
        return foundFaculty;
    }

    public List<Faculty> getFaculties() {
        return new ArrayList<>(faculties);
    }

    public List<Department> getDepartments() {
        return new ArrayList<>(departments);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public List<Teacher> getTeachers() {
        return new ArrayList<>(teachers);
    }
}
