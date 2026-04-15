package ui;

import domain.*;
import exceptions.*;
import services.*;
import validators.*;

import java.util.*;

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
                        printAll(DepartmentService.getAll());
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

    public static void createForm(){
        System.out.println("\nВи успішно додали кафедру: \n" + DepartmentService.createNewAndAdd(getId(),
                getName(), getFaculty(), getHeadOfDepartment(), getLocation()));
        Department createdDepartment = DepartmentService.findLastAdded();
        Teacher head = createdDepartment.getHeadOfDepartment();
        head.setDepartment(createdDepartment);
        head.setFaculty(createdDepartment.getFaculty());
    }

    private static String getId(){
        String id = InputReader.readLine("Введіть унікальний ідентифікатор кафедри: ", 4, 4);
        while(true){
            try{
                DepartmentValidator.isIdValid(id);
                break;
            }catch(DuplicateIdException e){
                System.out.println(e.getMessage());
            }
            id = InputReader.readLine("Введіть унікальний ідентифікатор кафедри: ", 4, 4);
        }
        return id;
    }

    private static String getName(){
        String name = InputReader.readLine("Введіть назву кафедри: ", 1, 100);
        while(true){
            try{
                DepartmentValidator.isNameValid(name);
                break;
            }catch(DuplicateNameException e){
                System.out.println(e.getMessage());
            }
            name = InputReader.readLine("Введіть назву кафедри: ", 1, 100);
        }
        return name;
    }

    private static Faculty getFaculty(){
        while(true){
            int howToChooseFaculty = InputReader.readInt("Виберіть, як хочете призначити кафедру до факультету: " +
                    "\n1 - вибрати з уже існуючих факультетів" +
                    "\n2 - створити новий факультет та призначити кафедру", 1,2);
            switch (howToChooseFaculty){
                //choose from existing
                case 1:
                    if(FacultyMenu.printAll(FacultyService.getAll())){
                        String id = InputReader.readLine("\nВведіть унікальний ідентифікатор факультету, який хочете вибрати: ", 7, 7);
                        Optional<Faculty> maybeFaculty = FacultyService.findById(id);
                        if(maybeFaculty.isPresent()){
                            Faculty foundFaculty = maybeFaculty.get();
                            System.out.println("\nКафедра буде призначена до факультету " + foundFaculty.getFullName() + ".");
                            return foundFaculty;
                        }else{
                            System.out.println("\nФакультет з унікальним ідентифікатором " + id + " не знайдено.");
                        }
                    }else{
                        System.out.println("Немає з чого вибрати.");
                    }
                    break;
                // create new
                case 2:
                    FacultyMenu.createForm();
                    Faculty faculty = FacultyService.findLastAdded();
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
            switch (howToChooseHeadOfDepartment){
                //choose from existing
                case 1:
                    if(TeacherMenu.printAll(TeacherService.getAll())){
                        String id = InputReader.readLine("\nВведіть унікальний ідентифікатор викладача, якого хочете вибрати: ", 5, 5);
                        Optional<Teacher> maybeTeacher = TeacherService.findById(id);
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
                    TeacherMenu.createForm(false);
                    Teacher headOfDepartment = TeacherService.findLastAdded();
                    headOfDepartment.setPosition(Position.HEAD);
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
        if(printAll(DepartmentService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор кафедри, яку хочете змінити: ", 4, 4);
            Optional<Department> maybeDepartment = DepartmentService.findById(id);
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
                            foundDepartment.setName(getName());
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
                System.out.println("\nКафедру з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає що змінювати.");
        }
    }

    private static void deleteForm(){
        if(printAll(DepartmentService.getAll())) {
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор кафедри, яку хочете видалити: ", 4, 4);
            Optional<Department> maybeDepartment = DepartmentService.findById(id);
            if (maybeDepartment.isPresent()) {
                Department foundDepartment = maybeDepartment.get();
                System.out.println("\nУВАГА!!!");
                System.out.println("Після видалення кафедри всі студенти та викладачі, що приписані до цього факультету, більше не будуть до нього приписані.");
                int makingSure = InputReader.readInt("Введіть 1, якщо точно хочете видалити, або 0, щоби відмінити дію: ", 0, 1);
                if(makingSure==1){
                    DepartmentService.delete(foundDepartment);
                    System.out.println("\nВи успішно видалили кафедру " + foundDepartment.getName() + ". Оновлені дані: ");
                    printAll(DepartmentService.getAll());
                }
            }else{
                System.out.println("\nКафедру з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає що видаляти.");
        }
    }

}
