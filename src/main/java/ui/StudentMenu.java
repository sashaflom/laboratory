package ui;

import domain.*;
import services.DepartmentService;
import services.FacultyService;
import services.StudentService;
import validators.InputReader;
import validators.PersonValidator;
import validators.StudentValidator;

import java.util.List;
import java.util.Optional;

public class StudentMenu {

    public static void selectOperation(){
        while(true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити: " +
                    "\n1 - створити нового студента" +
                    "\n2 - побачити всіх студентів" +
                    "\n3 - редагувати існуючого студента" +
                    "\n4 - видалити існуючого студента" +
                    "\n0 - повернутись на крок назад", 0, 4);
            switch (whatToDo){
                // create a new one was chosen
                case 1:
                    int makingSure = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure==1){
                        createForm();
                    }
                    break;
                // see existing one was chosen
                case 2:
                    int makingSure2 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure2==1){
                        printAll(StudentService.getAll());
                    }
                    break;
                // change existing one was chosen
                case 3:
                    int makingSure3 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure3==1){
                        changeForm();
                    }
                    //Repository.getInstance().changeFaculty();
                    break;
                // delete existing one was chosen
                case 4:
                    int makingSure4 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure4==1){
                        deleteForm();
                    }
                    //Repository.getInstance().deleteFaculty();
                    break;
                // exit was chosen
                case 0:
                    break;
            }
            if(whatToDo==0) break;
        }
    }

    public static void createForm(){
        System.out.println("\nВи успішно додали студента: \n" + StudentService.createNewAndAdd(getId(),
                getLastName(), getFirstName(), getPatronymic(), getBirthDate(), getEmail(), getPhoneNumber(),
                getDepartment(), getStudentId(), getCourse(), getGroup(), getEnrollmentYear(),
                getEducationForm(), getStatus()));
    }

    private static String getId(){
        String id = InputReader.readLine("Введіть унікальний ідентифікатор з 5 знаків: ", 5, 5);
        while(!StudentValidator.isIdValid(id)){
            System.out.println("Помилка! Студент з таким унікальним ідентифікатором уже існує.");
            id = InputReader.readLine("Введіть унікальний ідентифікатор з 5 знаків: ", 5, 5);
        }
        return id;
    }

    private static String getLastName(){
        String lastName = InputReader.readLine("Введіть прізвище: ", 1, 30);
        return lastName;
    }

    private static String getFirstName(){
        String firstName = InputReader.readLine("Введіть ім'я: ", 1, 30);
        return firstName;
    }

    private static String getPatronymic(){
        String patronymic = InputReader.readLine("Введіть побатькові: ", 1, 30);
        return patronymic;
    }

    private static String getBirthDate(){
        int bdYear = InputReader.readInt("Введіть рік народження: ", 1900, 2026);
        int bdMonth = InputReader.readInt("Введіть місяць народження цифрою: ", 1, 12);
        int bdDay = InputReader.readInt("Введіть день народження цифрою: ", 1, 31);
        String birthDate = bdDay + "." + bdMonth + "." + bdYear;
        return birthDate;
    }

    private static String getEmail(){
        String email = InputReader.readLine("Введіть email: ", 1, 40);
        while(!PersonValidator.isEmailValid(email)){
            System.out.println("Помилка! Email має бути формату xxx@ukma.edu.ua.");
            email = InputReader.readLine("Введіть email: ", 1, 40);
        }
        return email;
    }

    private static String getPhoneNumber(){
        String phoneNumber = InputReader.readLine("Введіть номер телефону: ", 10, 13);
        while(!PersonValidator.isPhoneNumberValid(phoneNumber)){
            System.out.println("Помилка! Некоректний формат номеру телефону.");
            phoneNumber = InputReader.readLine("Введіть номер телефону: ", 10, 13);
        }
        return phoneNumber;
    }

    private static Department getDepartment(){
        while (true){
            int howToChooseDepartment = InputReader.readInt("Виберіть, як хочете призначити студента кафедрі: " +
                    "\n1 - вибрати з уже існуючих кафедр" +
                    "\n2 - створити нову кафедру та призначити до неї студента", 1,2);
            switch (howToChooseDepartment){
                //choose from existing
                case 1:
                    if(DepartmentMenu.printAll(DepartmentService.getAll())){
                        String uniqueCode = InputReader.readLine("\nВведіть унікальний код кафедри, яку хочете обрати: ", 4, 4);
                        Optional<Department> maybeDepartment = DepartmentService.findById(uniqueCode);
                        if(maybeDepartment.isPresent()){
                            Department foundDepartment = maybeDepartment.get();
                            System.out.println("\nЦей студент буде на кафедрі " + foundDepartment.getName() + ".");
                            return foundDepartment;
                        }else{
                            System.out.println("\nКафедру з унікальним кодом " + uniqueCode + " не знайдено.");
                        }
                    }else{
                        System.out.println("Немає з чого обрати.");
                    }
                    break;
                // create new
                case 2:
                    DepartmentMenu.createForm();
                    Department department = DepartmentService.findLastAdded();
                    System.out.println("\nЦей студент буде на кафедрі " + department.getName() + ".");
                    return department;
            }
        }
    }

    private static String getStudentId(){
        String id = InputReader.readLine("Введіть номер залікової книжки (8 знаків): ", 8, 8);
        while(!StudentValidator.isStudentIdValid(id)){
            System.out.println("Помилка! Студент з таким номером залікової книжки уже існує.");
            id = InputReader.readLine("Введіть номер залікової книжки (8 знаків): ", 8, 8);
        }
        return id;
    }

    private static int getCourse(){
        int course = InputReader.readInt("Введіть курс студента (1-6): ", 1, 6);
        return course;
    }

    private static int getGroup(){
        int group = InputReader.readInt("Введіть номер групи: ", 1, 10);
        return group;
    }

    private static int getEnrollmentYear(){
        int enrollmentYear = InputReader.readInt("Введіть рік вступу: ", 2020, 2025);
        return enrollmentYear;
    }

    private static EducationForm getEducationForm(){
        int indexOfEducationForm = InputReader.readInt("Виберіть форму навчання: " +
                "\n0 - Бюджет " +
                "\n1 - Контракт", 0, 1);
        return EducationForm.values()[indexOfEducationForm];
    }

    private static StudentStatus getStatus(){
        int indexOfStatus = InputReader.readInt("Виберіть статус студента: " +
                "\n0 - Навчається " +
                "\n1 - Академвідпустка " +
                "\n2 - Відрахований", 0, 2);
        return StudentStatus.values()[indexOfStatus];
    }

    public static boolean printAll(List<Student> allStudents){
        if(allStudents.size()!=0){
            System.out.println("\nСПИСОК СТУДЕНТІВ:");
            int count = 1;
            for(Student student : allStudents){
                System.out.println(count + ". " + student);
                count++;
            }
            return true;
        }else{
            System.out.println("\nСписок студентів порожній.");
        }
        return false;
    }

    private static void changeForm(){
        if(printAll(StudentService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор студента, якого хочете змінити: ", 5, 5);
            Optional<Student> maybeStudent = StudentService.findById(id);
            if(maybeStudent.isPresent()){
                Student foundStudent = maybeStudent.get();
                while(true){
                    int whatToChange = InputReader.readInt("\nВиберіть, що ви хочете змінити:" +
                            "\n1 - прізвище" +
                            "\n2 - ім'я" +
                            "\n3 - побатькові" +
                            "\n4 - дату народження" +
                            "\n5 - email" +
                            "\n6 - номер телефону" +
                            "\n7 - кафедру" +
                            "\n8 - номер залікової книжки" +
                            "\n9 - курс" +
                            "\n10 - групу" +
                            "\n11 - рік вступу" +
                            "\n12 - форму навчання" +
                            "\n13 - статус навчання" +
                            "\n0 - повернутись на крок назад", 0, 13);
                    switch (whatToChange){
                        // last name to change
                        case 1:
                            foundStudent.setLastname(getLastName());
                            break;
                        // first name to change
                        case 2:
                            foundStudent.setFirstname(getFirstName());
                            break;
                        // patronymic to change
                        case 3:
                            foundStudent.setPatronymic(getPatronymic());
                            break;
                        // birth date to change
                        case 4:
                            foundStudent.setBirthDate(getBirthDate());
                            break;
                        // email to change
                        case 5:
                            foundStudent.setEmail(getEmail());
                            break;
                        // phone number to change
                        case 6:
                            foundStudent.setPhoneNumber(getPhoneNumber());
                            break;
                        //  department to change
                        case 7:
                            Department newDepartment = getDepartment();
                            foundStudent.setDepartment(newDepartment);
                            foundStudent.setFaculty(newDepartment.getFaculty());
                            break;
                        //  student id to change
                        case 8:
                            foundStudent.setStudentId(getStudentId());
                            break;
                        // course to change
                        case 9:
                            foundStudent.setCourse(getCourse());
                            break;
                        // group to change
                        case 10:
                            foundStudent.setGroup(getGroup());
                            break;
                        // enrollment year to change
                        case 11:
                            foundStudent.setEnrollmentYear(getEnrollmentYear());
                            break;
                        // education form to change
                        case 12:
                            foundStudent.setEducationForm(getEducationForm());
                            break;
                        // status to change
                        case 13:
                            foundStudent.setStatus(getStatus());
                            break;
                        // exit
                        case 0:
                            break;
                    }
                    if(whatToChange==0) break;
                    System.out.println("\nЗміни проведені успішно. Оновлені дані: \n" + foundStudent);
                }
            }else{
                System.out.println("\nСтудента з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого змінювати.");
        }
    }

    private static void deleteForm(){
        if(printAll(StudentService.getAll())) {
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор студента, якого хочете видалити: ", 5, 5);
            Optional<Student> maybeStudent = StudentService.findById(id);
            if (maybeStudent.isPresent()) {
                Student foundStudent = maybeStudent.get();
                StudentService.delete(foundStudent);
                System.out.println("\nВи успішно видалили студента. Оновлені дані: ");
                printAll(StudentService.getAll());
            }else{
                System.out.println("\nСтудента з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого видаляти.");
        }
    }

    public static void find(){
        while(true){
            if (StudentService.isThereSomethingInRepository()){
                int howToFind = InputReader.readInt("\nОберіть критерій пошуку: " +
                        "\n1 - за ПІБ" +
                        "\n2 - за курсом" +
                        "\n3 - за групою" +
                        "\n0 - повернутись на крок назад", 0, 3);
                switch (howToFind){
                    // find by full name
                    case 1:
                        findByFullName();
                        break;
                    // find by course
                    case 2:
                        findByCourse();
                        break;
                    // find by group
                    case 3:
                        findByGroup();
                        break;
                    // exit
                    case 0:
                        break;
                }
                if(howToFind==0) break;
            }else{
                System.out.println("\nСписок студентів порожній, ви не можете нікого знайти.");
                break;
            }
        }
    }

    private static void findByFullName(){
        String fullName = getLastName() + " " + getFirstName() + " " + getPatronymic();
        List<Student> foundStudents = StudentService.findAllByFullName(fullName);
        if(foundStudents.size()!=0){
            System.out.println("\nЗнайдені студенти з ПІБ " + fullName + ":");
            int count = 1;
            for(Student student : foundStudents){
                System.out.println(count + ". " + student);
                count++;
            }
        }else{
            System.out.println("\nCтудентів з ПІБ " + fullName + " не знайдено.");
        }
    }

    private static void findByCourse(){
        int course = getCourse();
        List<Student> foundStudents = StudentService.findAllByCourse(course);
        if(foundStudents.size()!=0){
            System.out.println("\nЗнайдені студенти на " + course + " курсі:");
            int count = 1;
            for(Student student : foundStudents){
                System.out.println(count + ". " + student);
                count++;
            }
        }else{
            System.out.println("\nCтудентів на " + course + " курсі не знайдено.");
        }
    }

    private static void findByGroup(){
        int group = getGroup();
        List<Student> foundStudents = StudentService.findAllByGroup(group);
        if(foundStudents.size()!=0){
            System.out.println("\nЗнайдені студенти в групі " + group + ":");
            int count = 1;
            for(Student student : foundStudents){
                System.out.println(count + ". " + student);
                count++;
            }
        }else{
            System.out.println("\nCтудентів в групі " + group + " не знайдено.");
        }
    }

    public static void report(){
        while(true){
            int whatToShow = InputReader.readInt("\nВиберіть конкретний звіт: " +
                    "\n1 - всі студенти, впорядковані за курсом" +
                    "\n2 - всі студенти, впорядковані за алфавітом" +
                    "\n3 - студенти певної кафедри" +
                    "\n4 - всі студенти певного факультету, впорядковані за алфавітом" +
                    "\n0 - повернутись на крок назад", 0, 4);
            switch (whatToShow){
                //all students sorted by course
                case 1:
                    printAllStudentsSortedByCourse();
                    break;
                //all students sorted by alphabet
                case 2:
                    printAllStudentsSortedByAlphabet();
                    break;
                //student in department
                case 3:
                    chooseDepartment();
                    break;
                //student in faculty (get faculty first) sorted by alphabet
                case 4:
                    chooseFaculty();
                    break;
                //exit was chosen
                case 0:
                    break;
            }
            if(whatToShow==0) break;
        }
    }

    private static void printAllStudentsSortedByCourse(){
        List<Student> sorted = StudentService.sortAllByCourse();
        if(sorted.isEmpty()){
            System.out.println("\nНемає жодного студенту, звіт порожній.");
        }else{
            System.out.println("\nСПИСОК ВСІХ СТУДЕНТІВ, ВІДСОРТОВАНИХ ЗА КУРСОМ:");
            int count = 1;
            for(Student student : sorted){
                System.out.println(count + ". " + student);
                count++;
            }
        }
    }

    private static void printAllStudentsSortedByAlphabet(){
        List<Student> sorted = StudentService.sortAllByAlphabet();
        if(sorted.isEmpty()){
            System.out.println("\nНемає жодного студенту, звіт порожній.");
        }else{
            System.out.println("\nСПИСОК ВСІХ СТУДЕНТІВ, ВІДСОРТОВАНИХ ЗА АЛФАВІТОМ:");
            int count = 1;
            for(Student student : sorted){
                System.out.println(count + ". " + student);
                count++;
            }
        }
    }

    private static void chooseDepartment(){
        if(DepartmentMenu.printAll(DepartmentService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор кафедри, " +
                    "студентів якої ви хочете побачити: ", 4, 4);
            Optional<Department> maybeDepartment = DepartmentService.findById(id);
            if(maybeDepartment.isPresent()){
                Department foundDepartment = maybeDepartment.get();
                List<Student> inDepartment = StudentService.findAllByDepartment(foundDepartment);
                while(true){
                    int whatToShow = InputReader.readInt("\nВиберіть конкретний звіт:" +
                            "\n1 - всі студенти цієї кафедри, впорядковані за курсами" +
                            "\n2 - всі студенти цієї кафедри, впорядковані за алфавітом" +
                            "\n3 - всі студенти цієї кафедри певного курсу, впорядковані за алфавітом" +
                            "\n0 - повернутись на крок назад", 0, 3);
                    switch (whatToShow){
                        // student in department sorted by course
                        case 1:
                            printAllStudentsInDepartmentSortedByCourse(inDepartment, foundDepartment.getName());
                            break;
                        // student in department sorted by alphabet
                        case 2:
                            printAllStudentsInDepartmentSortedByAlphabet(inDepartment, foundDepartment.getName());
                            break;
                        // student in department with entered course (get course first) sorted by alphabet
                        case 3:
                            int course = getCourse();
                            printAllStudentsInDepartmentWithCourseSortedByAlphabet(inDepartment, foundDepartment.getName(), course);
                            break;
                        // exit
                        case 0:
                            break;
                    }
                    if(whatToShow==0) break;
                }
            }else{
                System.out.println("\nКафедру з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Звіт порожній.");
        }
    }

    private static void printAllStudentsInDepartmentSortedByCourse(List<Student> inDepartment, String departmentName){
        List<Student> sorted = StudentService.sortPartByCourse(inDepartment);
        if(sorted.isEmpty()){
            System.out.println("\nНемає жодного студенту на кафедрі " + departmentName + ", звіт порожній.");
        }else{
            System.out.println("\nСПИСОК ВСІХ СТУДЕНТІВ НА КАФЕДРІ " + departmentName.toUpperCase() + ", ВІДСОРТОВАНИХ ЗА КУРСОМ:");
            int count = 1;
            for(Student student : sorted){
                System.out.println(count + ". " + student);
                count++;
            }
        }
    }

    private static void printAllStudentsInDepartmentSortedByAlphabet(List<Student> inDepartment, String departmentName){
        List<Student> sorted = StudentService.sortPartByAlphabet(inDepartment);;
        if(sorted.isEmpty()){
            System.out.println("\nНемає жодного студенту на кафедрі " + departmentName + ", звіт порожній.");
        }else{
            System.out.println("\nСПИСОК ВСІХ СТУДЕНТІВ НА КАФЕДРІ " + departmentName.toUpperCase() + ", ВІДСОРТОВАНИХ ЗА АЛФАВІТОМ:");
            int count = 1;
            for(Student student : sorted){
                System.out.println(count + ". " + student);
                count++;
            }
        }
    }

    private static void printAllStudentsInDepartmentWithCourseSortedByAlphabet(List<Student> inDepartment, String departmentName, int course){
        List<Student> sorted = StudentService.sortPartByAlphabet(StudentService.findPartByCourse(course, inDepartment));;
        if(sorted.isEmpty()){
            System.out.println("\nНемає жодного студенту на кафедрі " + departmentName + " на курсі " + course + ", звіт порожній.");
        }else{
            System.out.println("\nСПИСОК ВСІХ СТУДЕНТІВ НА КАФЕДРІ " + departmentName.toUpperCase() + " НА КУРСІ " + course + ", ВІДСОРТОВАНИХ ЗА АЛФАВІТОМ:");
            int count = 1;
            for(Student student : sorted){
                System.out.println(count + ". " + student);
                count++;
            }
        }
    }

    private static void chooseFaculty(){
        if(FacultyMenu.printAll(FacultyService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор факультету, " +
                    "студентів якого хочете побачити: ", 7, 7);
            Optional<Faculty> maybeFaculty = FacultyService.findById(id);
            if(maybeFaculty.isPresent()){
                Faculty foundFaculty = maybeFaculty.get();
                List<Student> inFaculty = StudentService.findAllByFaculty(foundFaculty);
                printAllStudentsInFacultySortedByAlphabet(inFaculty, foundFaculty.getShortName());
            }else{
                System.out.println("\nФакультету з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Звіт порожній.");
        }
    }

    private static void printAllStudentsInFacultySortedByAlphabet(List<Student> inFaculty, String facultyName){
        List<Student> sorted = StudentService.sortPartByAlphabet(inFaculty);;
        if(sorted.isEmpty()){
            System.out.println("\nНемає жодного студенту на факультеті " + facultyName + ", звіт порожній.");
        }else{
            System.out.println("\nСПИСОК ВСІХ СТУДЕНТІВ НА ФАКУЛЬТЕТІ " + facultyName.toUpperCase() + ", ВІДСОРТОВАНИХ ЗА АЛФАВІТОМ:");
            int count = 1;
            for(Student student : sorted){
                System.out.println(count + ". " + student);
                count++;
            }
        }
    }

}
