package ui;

import domain.*;
import services.DepartmentService;
import services.FacultyService;
import services.TeacherService;
import validators.InputReader;
import validators.PersonValidator;
import validators.TeacherValidator;

import java.util.List;
import java.util.Optional;
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
                        createForm();
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
        System.out.println("\nВи успішно додали викладача: \n" + TeacherService.createNewAndAdd(getId(), getLastName(), getFirstName(),
                getPatronymic(), getBirthDate(), getEmail(), getPhoneNumber(), null, getPosition(),
                getAcademicDegree(), getAcademicTitle(), getHireDate(), getWorkload()));
    }

    private static String getId(){
        String id = InputReader.readLine("Введіть унікальний ідентифікатор з 5 знаків: ", 5, 5);
        while(!TeacherValidator.isIdValid(id)){
            System.out.println("Помилка! Викладач з таким унікальним ідентифікатором уже існує.");
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
        int bdDay = InputReader.readInt("Введіть день народження: ", 1, 31);
        int bdMonth = InputReader.readInt("Введіть місяць народження (цифрою): ", 1, 12);
        int bdYear = InputReader.readInt("Введіть рік народження: ", 1900, 2026);
        return LocalDate.of(bdYear, bdMonth, bdDay);
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

    public static Department getDepartment(){
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

    private static Position getPosition(){
        int indexOfPosition = InputReader.readInt("Виберіть посаду:" +
                "\n0 - Викладач" +
                "\n1 - Декан факультету" +
                "\n2 - Завідувач кафедри", 0, 2);
        return Position.values()[indexOfPosition];
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

    private static String getHireDate(){
        int hYear = InputReader.readInt("Введіть рік прийняття на роботу: ", 1900, 2026);
        int hMonth = InputReader.readInt("Введіть місяць прийняття на роботу цифрою: ", 1, 12);
        int hDay = InputReader.readInt("Введіть день прийняття на роботу цифрою: ", 1, 31);
        String hireDate = hDay + "." + hMonth + "." + hYear;
        return hireDate;
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
                            Department newDepartment = getDepartment();
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
        if(foundTeachers.size()!=0){
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
        // code for report
    }

    private static void chooseDepartment(){
        if(DepartmentMenu.printAll(DepartmentService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор кафедри, " +
                    "викладачів якої ви хочете побачити: ", 4, 4);
            Optional<Department> maybeDepartment = DepartmentService.findById(id);
            if(maybeDepartment.isPresent()){
                Department foundDepartment = maybeDepartment.get();
                printAllTeachersInDepartmentSortedByAlphabet();
            }else{
                System.out.println("\nКафедру з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Звіт порожній.");
        }
    }

    private static void printAllTeachersInDepartmentSortedByAlphabet(){
        // code for report
    }

    private static void chooseFaculty(){
        if(FacultyMenu.printAll(FacultyService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор факультету, " +
                    "викладачів якого хочете побачити: ", 7, 7);
            Optional<Faculty> maybeFaculty = FacultyService.findById(id);
            if(maybeFaculty.isPresent()){
                Faculty foundFaculty = maybeFaculty.get();
                printAllTeachersInFacultySortedByAlphabet();
            }else{
                System.out.println("\nФакультету з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Звіт порожній.");
        }
    }

    private static void printAllTeachersInFacultySortedByAlphabet(){
        // code for report
    }

}
