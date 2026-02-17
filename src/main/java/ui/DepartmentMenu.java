package ui;

import domain.Department;
import domain.Faculty;
import domain.Position;
import domain.Teacher;
import repositories.DepartmentRepository;
import repositories.Repository;
import services.DepartmentService;
import services.FacultyService;
import services.TeacherService;
import validators.DepartmentValidator;
import validators.FacultyValidator;
import validators.InputReader;

import java.util.List;
import java.util.Optional;

public class DepartmentMenu {

    public static void selectOperation(){
        while(true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити: " +
                    "\n1 - створити нову кафедру" +
                    "\n2 - побачити всі кафедри" +
                    "\n3 - редагувати існуючу кафедру" +
                    "\n4 - видалити існуючу кафедру" +
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
                        DepartmentService service = new DepartmentService(Repository.getInstance());
                        printAll(service.getAll());
                    }
                    //Repository.getInstance().showAllFaculties();
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
        DepartmentService service = new DepartmentService(Repository.getInstance());
        DepartmentValidator departmentValidator = new DepartmentValidator(Repository.getInstance());
        System.out.println("\nВи успішно додали кафедру: \n" + service.createNewAndAdd(getUniqueCode(departmentValidator),
                getName(departmentValidator), getFaculty(), getHeadOfDepartment(), getLocation()));
    }

    private static String getUniqueCode(DepartmentValidator validator){
        String uniqueCode = InputReader.readLine("Введіть унікальний код кафедри: ", 4, 4);
        while(!validator.isUniqueCodeValid(uniqueCode)){
            System.out.println("Помилка! Кафедра з таким унікальним кодом уже існує.");
            uniqueCode = InputReader.readLine("Введіть унікальний код кафедри: ", 4, 4);
        }
        return uniqueCode;
    }

    private static String getName(DepartmentValidator validator){
        String name = InputReader.readLine("Введіть назву кафедри: ", 1, 40);
        while(!validator.isNameValid(name)){
            System.out.println("Помилка! Кафедра з такою назвою уже існує.");
            name = InputReader.readLine("Введіть назву кафедри: ", 1, 40);
        }
        return name;
    }

    private static Faculty getFaculty(){
        while(true){
            int howToChooseFaculty = InputReader.readInt("Виберіть, як хочете призначити кафедру до факультету: " +
                    "\n1 - вибрати з уже існуючих факультетів" +
                    "\n2 - створити новий факультет та призначити кафедру", 1,2);
            FacultyService facultyService = new FacultyService(Repository.getInstance());
            switch (howToChooseFaculty){
                //choose from existing
                case 1:
                    if(FacultyMenu.printAll(facultyService.getAll())){
                        String uniqueCode = InputReader.readLine("\nВведіть унікальний код факультету, який хочете вибрати: ", 7, 7);
                        Optional<Faculty> maybeFaculty = facultyService.findByUniqueCode(uniqueCode);
                        if(maybeFaculty.isPresent()){
                            Faculty foundFaculty = maybeFaculty.get();
                            System.out.println("\nКафедра буде призначена до факультету " + foundFaculty.getFullName() + ".");
                            return foundFaculty;
                        }else{
                            System.out.println("\nФакультет з унікальним кодом " + uniqueCode + " не знайдено.");
                        }
                    }else{
                        System.out.println("Немає з чого вибрати.");
                    }
                    break;
                // create new
                case 2:
                    FacultyMenu.createForm();
                    Faculty faculty = facultyService.findLastAdded();
                    System.out.println("\nКафедра буде призначена до факультету " + faculty.getFullName() + ".");
                    return faculty;
            }
        }
    }

    private static Teacher getHeadOfDepartment(){
        while (true){
            int howToChooseHeadOfDepartment = InputReader.readInt("Виберіть, як хочете назначити завідувача кафедри: " +
                    "\n1 - вибрати з уже існуючих викладачів" +
                    "\n2 - створити нового викладача та назначити завідувачем кафедри", 1,2);
            TeacherService teacherService = new TeacherService(Repository.getInstance());
            switch (howToChooseHeadOfDepartment){
                //choose from existing
                case 1:
                    if(TeacherMenu.printAll(teacherService.getAll())){
                        String id = InputReader.readLine("\nВведіть унікальний ідентифікатор викладача, якого хочете вибрати: ", 5, 5);
                        Optional<Teacher> maybeTeacher = teacherService.findById(id);
                        if(maybeTeacher.isPresent()){
                            Teacher foundTeacher = maybeTeacher.get();
                            if(foundTeacher.getPosition()== Position.DEAN){
                                System.out.println("Цей викладач є деканом, ви не можете його назначити.");
                            }else if(foundTeacher.getPosition()==Position.HEAD){
                                System.out.println("Цей викладач уже є завідувачем кафедри, ви не можете його назначити.");
                            }else{
                                System.out.println("Викладач " + foundTeacher.getFullName() + " буде завідувачем цієї кафедри.");
                                foundTeacher.setPosition(Position.HEAD);
                                return foundTeacher;
                            }
                        }else{
                            System.out.println("\nВикладача з унікальним ідентифікатором " + id + " не знайдено.");
                        }
                    }else{
                        System.out.println("Немає кого назначити.");
                    }
                    break;
                // create new
                case 2:
                    TeacherMenu.createForm();
                    Teacher headOfDepartment = teacherService.findLastAdded();
                    System.out.println("Викладач " + headOfDepartment.getFullName() + " буде завідувачем цієї кафедри.");
                    return headOfDepartment;
            }
        }
    }

    private static String getLocation(){
        String location = InputReader.readLine("Введіть локацію кафедри (номер корпусу чи аудиторії): ", 1, 40);
        return location;
    }

    public static boolean printAll(List<Department> allDepartments){
        if(allDepartments.size()!=0){
            System.out.println("\nСПИСОК КАФЕДР:");
            int count = 1;
            for(Department department : allDepartments){
                System.out.println(count + ". " + department);
                count++;
            }
            return true;
        }else{
            System.out.println("\nСписок кафедр порожній.");
        }
        return false;
    }

    private static void changeForm(){
        DepartmentService service = new DepartmentService(Repository.getInstance());
        DepartmentValidator departmentValidator = new DepartmentValidator(Repository.getInstance());
        if(printAll(service.getAll())){
            String uniqueCode = InputReader.readLine("\nВведіть унікальний код кафедри, яку хочете змінити: ", 4, 4);
            Optional<Department> maybeDepartment = service.findByUniqueCode(uniqueCode);
            if(maybeDepartment.isPresent()){
                Department foundDepartment = maybeDepartment.get();
                while(true){
                    int whatToChange = InputReader.readInt("\nВиберіть, що ви хочете змінити:" +
                            "\n1 - назву" +
                            "\n2 - факультет, до якого призначена кафедра" +
                            "\n3 - завідувача кафедри" +
                            "\n4 - локацію" +
                            "\n0 - повернутись на крок назад", 0, 4);
                    switch (whatToChange){
                        // name to change
                        case 1:
                            foundDepartment.setName(getName(departmentValidator));
                            break;
                        // faculty to change
                        case 2:
                            foundDepartment.setFaculty(getFaculty());
                            break;
                        // head of department to change
                        case 3:
                            foundDepartment.getHeadOfDepartment().setPosition(Position.TEACHER);
                            foundDepartment.setHeadOfDepartment(getHeadOfDepartment());
                            break;
                        // location to change
                        case 4:
                            foundDepartment.setLocation(getLocation());
                            break;
                        // exit
                        case 0:
                            break;
                    }
                    if(whatToChange==0) break;
                    System.out.println("\nЗміни проведені успішно. Оновлені дані: \n" + foundDepartment);
                }
            }else{
                System.out.println("\nКафедру з унікальним кодом " + uniqueCode + " не знайдено.");
            }
        }else{
            System.out.println("Немає що змінювати.");
        }
    }

    private static void deleteForm(){
        DepartmentService service = new DepartmentService(Repository.getInstance());
        if(printAll(service.getAll())) {
            String uniqueCode = InputReader.readLine("\nВведіть унікальний код кафедри, яку хочете видалити: ", 4, 4);
            Optional<Department> maybeDepartment = service.findByUniqueCode(uniqueCode);
            if (maybeDepartment.isPresent()) {
                Department foundDepartment = maybeDepartment.get();
                service.delete(foundDepartment);
                System.out.println("\nВи успішно видалили кафедру. Оновлені дані: ");
                printAll(service.getAll());
            }else{
                System.out.println("\nКафедру з унікальним кодом " + uniqueCode + " не знайдено.");
            }
        }else{
            System.out.println("Немає що видаляти.");
        }
    }

}
