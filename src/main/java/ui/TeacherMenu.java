package ui;

import domain.*;
import exceptions.*;
import services.*;
import validators.*;

import java.util.*;
import java.time.LocalDate;

public class TeacherMenu {

    public static void selectOperation(){
        while(true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити: " +
                    "\n1 - створити нового викладча" +
                    "\n2 - побачити всіх викладачів" +
                    "\n3 - редагувати існуючого викладача" +
                    "\n4 - видалити існуючого викладача" +
                    "\n0 - повернутись на крок назад", 0, 4);
            switch (whatToDo){
                // create a new one was chosen
                case 1:
                    int makingSure = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure==1){
                        createForm(true);
                    }
                    break;
                // see existing one was chosen
                case 2:
                    int makingSure2 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure2==1){
                        printAll(TeacherService.getAll());
                    }
                    break;
                // change existing one was chosen
                case 3:
                    int makingSure3 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure3==1){
                        changeForm();
                    }
                    break;
                // delete existing one was chosen
                case 4:
                    int makingSure4 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure4==1){
                        deleteForm();
                    }
                    break;
                // exit was chosen
                case 0:
                    break;
            }
            if(whatToDo==0) break;
        }
    }

    public static void createForm(boolean whatToDoWithDepartment){
        System.out.println("\nВи успішно додали викладача: \n" + TeacherService.createNewAndAdd(getId(), getLastName(), getFirstName(),
                getPatronymic(), getBirthDate(), getEmail(), getPhoneNumber(), getDepartment(whatToDoWithDepartment), Position.TEACHER,
                getAcademicDegree(), getAcademicTitle(), getHireDate(), getWorkload()));
    }

    private static String getId(){
        String id = InputReader.readLine("Введіть унікальний ідентифікатор з 5 знаків: ", 5, 5);
        while(true){
            try{
                TeacherValidator.isIdValid(id);
                break;
            } catch (DuplicateIdException e) {
                System.out.println(e.getMessage());
            }
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

    private static LocalDate getBirthDate(){
        int bdDay = InputReader.readInt("Введіть день народження цифрою: ", 1, 31);
        int bdMonth = InputReader.readInt("Введіть місяць народження цифрою: ", 1, 12);
        int bdYear = InputReader.readInt("Введіть рік народження: ", 1900, 2026);
        while(!PersonValidator.isBirthDateValid(bdYear, bdMonth, bdDay)){
            System.out.println("Помилка! Дата некоректна, перевірте що такий день є у цьому місяці.");
            bdDay = InputReader.readInt("Введіть день народження цифрою: ", 1, 31);
            bdMonth = InputReader.readInt("Введіть місяць народження цифрою: ", 1, 12);
            bdYear = InputReader.readInt("Введіть рік народження: ", 1900, 2026);
        }
        return LocalDate.of(bdYear, bdMonth, bdDay);
    }

    private static String getEmail(){
        String email = InputReader.readLine("Введіть email: ", 1, 40);
        while(true){
            try{
                PersonValidator.isEmailValid(email);
                break;
            }catch(InvalidEmailException e){
                System.out.println(e.getMessage());
            }
            email = InputReader.readLine("Введіть email: ", 1, 40);
        }
        return email;
    }

    private static String getPhoneNumber(){
        String phoneNumber = InputReader.readLine("Введіть номер телефону: ", 10, 13);
        while(true){
            try{
                PersonValidator.isPhoneNumberValid(phoneNumber);
                break;
            }catch(InvalidPhoneNumber e){
                System.out.println(e.getMessage());
            }
            phoneNumber = InputReader.readLine("Введіть номер телефону: ", 10, 13);
        }
        return phoneNumber;
    }

    public static Department getDepartment(boolean fromWhere){
        /*
        if we creating a teacher from menu "create a new one" or
        from "change teacher" (true) -> we need to get department
         */
        /*
        if we creating a teacher WHILE creating a faculty or department (false) -> return null
         */
        if(fromWhere){
            while (true){
                int howToChooseDepartment = InputReader.readInt("Виберіть, як хочете призначити викладача кафедрі: " +
                        "\n1 - вибрати з уже існуючих кафедр" +
                        "\n2 - створити нову кафедру та призначити до неї викладача", 1,2);
                switch (howToChooseDepartment){
                    //choose from existing
                    case 1:
                        if(DepartmentMenu.printAll(DepartmentService.getAll())){
                            String uniqueCode = InputReader.readLine("\nВведіть унікальний код кафедри, яку хочете обрати: ", 4, 4);
                            Optional<Department> maybeDepartment = DepartmentService.findById(uniqueCode);
                            if(maybeDepartment.isPresent()){
                                Department foundDepartment = maybeDepartment.get();
                                System.out.println("\nЦей викладач буде на кафедрі " + foundDepartment.getName() + ".");
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
                        System.out.println("\nЦей викладач буде на кафедрі " + department.getName() + ".");
                        return department;
                }
            }
        }
        return null;
    }

    private static AcademicDegree getAcademicDegree(){
        int indexOfAcademicDegree = InputReader.readInt("Виберіть науковий ступунь:" +
                "\n0 - Без ступеня" +
                "\n1 - Кандидат наук" +
                "\n2 - Доктор наук", 0, 2);
        return AcademicDegree.values()[indexOfAcademicDegree];
    }

    private static AcademicTitle getAcademicTitle(){
        int indexOfAcademicTitle = InputReader.readInt("Виберіть вчене звання:" +
                "\n0 - Без звання" +
                "\n1 - Доцент" +
                "\n2 - Професор", 0, 2);
        return AcademicTitle.values()[indexOfAcademicTitle];
    }

    private static LocalDate getHireDate(){
        int hDay = InputReader.readInt("Введіть день прийняття на роботу цифрою: ", 1, 31);
        int hMonth = InputReader.readInt("Введіть місяць прийняття на роботу цифрою: ", 1, 12);
        int hYear = InputReader.readInt("Введіть рік прийняття на роботу: ", 1900, 2026);
        while(!TeacherValidator.isHireDateValid(hYear, hMonth, hDay)){
            System.out.println("Помилка! Дата некоректна, перевірте що такий день є у цьому місяці.");
            hDay = InputReader.readInt("Введіть день прийняття на роботу цифрою: ", 1, 31);
            hMonth = InputReader.readInt("Введіть місяць прийняття на роботу цифрою: ", 1, 12);
            hYear = InputReader.readInt("Введіть рік прийняття на роботу: ", 1900, 2026);
        }
        return LocalDate.of(hYear, hMonth, hDay);
    }

    private static double getWorkload(){
        double workload = InputReader.readDouble("Введіть ставку: ", 10, 1000000);
        return workload;
    }

    public static boolean printAll(List<Teacher> allTeachers){
        if(allTeachers.size()!=0){
            System.out.println("\nСПИСОК ВИКЛАДАЧІВ:");
            int count = 1;
            for(Teacher teacher : allTeachers){
                System.out.println(count + ". " + teacher);
                count++;
            }
            return true;
        }else{
            System.out.println("\nСписок викладачів порожній.");
        }
        return false;
    }

    private static void changeForm(){
        if(printAll(TeacherService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор викладача, якого хочете змінити: ", 5, 5);
            Optional<Teacher> maybeTeacher = TeacherService.findById(id);
            if(maybeTeacher.isPresent()){
                Teacher foundTeacher = maybeTeacher.get();
                while(true){
                    int whatToChange = InputReader.readInt("\nВиберіть, що ви хочете змінити:" +
                            "\n1 - прізвище" +
                            "\n2 - ім'я" +
                            "\n3 - побатькові" +
                            "\n4 - дату народження" +
                            "\n5 - email" +
                            "\n6 - номер телефону" +
                            "\n7 - кафедру" +
                            "\n8 - науковий ступінь" +
                            "\n9 - вчене звання" +
                            "\n10 - дату прийняття на роботу" +
                            "\n11 - ставку" +
                            "\n0 - повернутись на крок назад", 0, 11);
                    switch (whatToChange){
                        // last name to change
                        case 1:
                            foundTeacher.setLastname(getLastName());
                            break;
                        // first name to change
                        case 2:
                            foundTeacher.setFirstname(getFirstName());
                            break;
                        // patronymic to change
                        case 3:
                            foundTeacher.setPatronymic(getPatronymic());
                            break;
                        // birth date to change
                        case 4:
                            foundTeacher.setBirthDate(getBirthDate());
                            break;
                        // email to change
                        case 5:
                            foundTeacher.setEmail(getEmail());
                            break;
                        // phone number to change
                        case 6:
                            foundTeacher.setPhoneNumber(getPhoneNumber());
                            break;
                        //  department to change
                        case 7:
                            Department newDepartment = getDepartment(true);
                            foundTeacher.setDepartment(newDepartment);
                            foundTeacher.setFaculty(newDepartment.getFaculty());
                            break;
                        //  academic degree to change
                        case 8:
                            foundTeacher.setAcademicDegree(getAcademicDegree());
                            break;
                        // academic title to change
                        case 9:
                            foundTeacher.setAcademicTitle(getAcademicTitle());
                            break;
                        // hire date to change
                        case 10:
                            foundTeacher.setHireDate(getHireDate());
                            break;
                        // workload to change
                        case 11:
                            foundTeacher.setWorkload(getWorkload());
                            break;
                        // exit
                        case 0:
                            break;
                    }
                    if(whatToChange==0) break;
                    System.out.println("\nЗміни проведені успішно. Оновлені дані: \n" + foundTeacher);
                }
            }else{
                System.out.println("\nВикладача з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого змінювати.");
        }
    }

    private static void deleteForm(){
        if(printAll(TeacherService.getAll())) {
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор викладача, якого хочете видалити: ", 5, 5);
            Optional<Teacher> maybeTeacher = TeacherService.findById(id);
            if (maybeTeacher.isPresent()) {
                Teacher foundTeacher = maybeTeacher.get();
                TeacherService.delete(foundTeacher);
                System.out.println("\nВи успішно видалили викладача. Оновлені дані: ");
                printAll(TeacherService.getAll());
            }else{
                System.out.println("\nВикладача з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого видаляти.");
        }
    }

    public static void find(){
        while(true){
            if (TeacherService.isThereSomethingInRepository()){
                int howToFind = InputReader.readInt("\nОберіть критерій пошуку: " +
                        "\n1 - за унікальним ідентифікатором" +
                        "\n2 - за ПІБ" +
                        "\n0 - повернутись на крок назад", 0, 2);
                switch (howToFind){
                    // find by ID
                    case 1:
                        findById();
                        break;
                    // find by full name
                    case 2:
                        findByFullName();
                        break;
                    // exit
                    case 0:
                        break;
                }
                if(howToFind==0) break;
            }else{
                System.out.println("\nСписок викладачів порожній, ви не можете нікого знайти.");
                break;
            }
        }
    }

    private static void findById(){
        String id = InputReader.readLine("Введіть унікальний ідентифікатор з 5 знаків: ", 5, 5);
        Optional<Teacher> maybeTeacher = TeacherService.findById(id);
        if(maybeTeacher.isPresent()){
            System.out.println("\nЗнайдений викладач з унікальним ідентифікатором " + id + ":");
            System.out.println(maybeTeacher.get());
        }else{
            System.out.println("\nВикладача з унікальним ідентифікатором " + id + " не знайдено.");
        }
    }

    private static void findByFullName(){
        String fullName = getLastName() + " " + getFirstName() + " " + getPatronymic();
        List<Teacher> foundTeachers = TeacherService.findByFullName(fullName);
        if(!foundTeachers.isEmpty()){
            System.out.println("\nЗнайдені викладачі з ПІБ " + fullName + ":");
            int count = 1;
            for(Teacher teacher : foundTeachers){
                System.out.println(count + ". " + teacher);
                count++;
            }
        }else{
            System.out.println("\nВикладачів з ПІБ " + fullName + " не знайдено.");
        }
    }

    public static void report(){
        while(true){
            int whatToShow = InputReader.readInt("\nВиберіть конкретний звіт: " +
                    "\n1 - всі викладачі, впорядковані за алфавітом" +
                    "\n2 - всі викладачі певної кафедри, впорядковані за алфавітом" +
                    "\n3 - всі викладачі певного факультету, впорядковані за алфавітом" +
                    "\n0 - повернутись на крок назад", 0, 3);
            switch (whatToShow){
                //all teachers sorted by alphabet
                case 1:
                    printAllTeachersSortedByAlphabet();
                    break;
                //teachers in department (get faculty first) sorted by alphabet
                case 2:
                    chooseDepartment();
                    break;
                //teachers in faculty (get faculty first) sorted by alphabet
                case 3:
                    chooseFaculty();
                    break;
                //exit was chosen
                case 0:
                    break;
            }
            if(whatToShow==0) break;
        }
    }

    private static void printAllTeachersSortedByAlphabet(){
        List <Teacher> sorted = TeacherService.getAllTeachersSortedByAlphabet();
        if (sorted.isEmpty()){
            System.out.println("\nЗвіт порожній, ще не було створено жодного викладача");
        }
        else {
            System.out.println("\nВсі викладачі впорядковані за алфавітом");
            int count = 1;
            for(Teacher teacher : sorted){
                System.out.println(count + ". " + teacher);
                count++;
            }
        }
    }

    private static void chooseDepartment(){
        if(DepartmentMenu.printAll(DepartmentService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор кафедри, " +
                    "викладачів якої ви хочете побачити: ", 4, 4);
            Optional<Department> maybeDepartment = DepartmentService.findById(id);
            if(maybeDepartment.isPresent()){
                Department foundDepartment = maybeDepartment.get();
                printAllTeachersInDepartmentSortedByAlphabet(foundDepartment);
            }else{
                System.out.println("\nКафедру з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Звіт порожній.");
        }
    }

    private static void printAllTeachersInDepartmentSortedByAlphabet(Department foundDepartment){
       List<Teacher> sorted = TeacherService.getAllTeachersSortedByDepartment(foundDepartment);
       if(sorted.isEmpty()){
           System.out.println("\nНа цій кафедрі немає викладачів");
       }
       else  {
           int count = 1;
           for(Teacher teacher : sorted){
               System.out.println(count + "." + teacher);
               count++;
           }
       }
    }

    private static void chooseFaculty(){
        if(FacultyMenu.printAll(FacultyService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор факультету, " +
                    "викладачів якого хочете побачити: ", 7, 7);
            Optional<Faculty> maybeFaculty = FacultyService.findById(id);
            if(maybeFaculty.isPresent()){
                Faculty foundFaculty = maybeFaculty.get();
                printAllTeachersInFacultySortedByAlphabet(foundFaculty);
            }else{
                System.out.println("\nФакультету з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Звіт порожній.");
        }
    }

    private static void printAllTeachersInFacultySortedByAlphabet(Faculty foundFaculty){
        List <Teacher> sorted = TeacherService.getAllTeachersSortedByFaculty(foundFaculty);
        if(sorted.isEmpty()){
            System.out.println("Цей факультет не містить викладачів");
        }
        else  {
            int count = 1;
            for(Teacher teacher : sorted){
                System.out.println(count + "." + teacher);
                count++;
            }
        }
    }

}
