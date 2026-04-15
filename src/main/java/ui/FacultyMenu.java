package ui;

import domain.*;
import exceptions.*;
import services.*;
import validators.*;

import java.util.*;

public class FacultyMenu {

    public static void selectOperation(){
        while(true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити: " +
                    "\n1 - створити новий факультет" +
                    "\n2 - побачити всі факультети" +
                    "\n3 - редагувати існуючий факультет" +
                    "\n4 - видалити існуючий факультет" +
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
                        printAll(FacultyService.getAll());
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
        System.out.println("\nВи успішно додали факультет: \n" + FacultyService.createNewAndAdd(getId(),
                getFullName(), getShortName(), getDean(), getContacts()));
    }

    private static String getId(){
        String id = InputReader.readLine("Введіть унікальний ідентифікатор з 7 знаків: ", 7, 7);
        while(true){
            try{
                FacultyValidator.isIdValid(id);
                break;
            }catch(DuplicateIdException e){
                System.out.println(e.getMessage());
            }
            id = InputReader.readLine("Введіть унікальний ідентифікатор з 7 знаків: ", 7, 7);
        }
        return id;
    }

    private static String getFullName(){
        String fullName = InputReader.readLine("Введіть повну назву: ", 5, 50);
        while (true){
            try{
                FacultyValidator.isFullNameValid(fullName);
                break;
            }catch(DuplicateNameException e){
                System.out.println(e.getMessage());
            }
            fullName = InputReader.readLine("Введіть повну назву: ", 5, 50);
        }
        return fullName;
    }

    private static String getShortName(){
        String shortName = InputReader.readLine("Введіть скорочену назву: ", 1, 10);
        while (true){
            try{
                FacultyValidator.isShortNameValid(shortName);
                break;
            }catch(DuplicateNameException e){
                System.out.println(e.getMessage());
            }
            shortName = InputReader.readLine("Введіть скорочену назву: ", 1, 10);
        }
        return shortName;
    }

    private static Teacher getDean(){
        while (true){
            int howToChooseDean = InputReader.readInt("Виберіть, як хочете назначити декана: " +
                    "\n1 - вибрати з уже існуючих викладачів" +
                    "\n2 - створити нового викладача та назначити деканом", 1,2);
            switch (howToChooseDean){
                //choose from existing
                case 1:
                    if(TeacherMenu.printAll(TeacherService.getAll())){
                        String id = InputReader.readLine("\nВведіть унікальний ідентифікатор викладача, якого хочете обрати: ", 5, 5);
                        Optional<Teacher> maybeTeacher = TeacherService.findById(id);
                        if(maybeTeacher.isPresent()){
                            Teacher foundTeacher = maybeTeacher.get();
                            if(foundTeacher.getPosition()== Position.DEAN){
                                System.out.println("Цей викладач уже є деканом, ви не можете його назначити.");
                            }else if(foundTeacher.getPosition()==Position.HEAD){
                                System.out.println("Цей викладач є завідувачем кафедри, ви не можете його назначити.");
                            }else{
                                System.out.println("\nВикладач " + foundTeacher.getFullName() + " буде деканом цього факультету.");
                                foundTeacher.setPosition(Position.DEAN);
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
                    Teacher dean = TeacherService.findLastAdded();
                    dean.setPosition(Position.DEAN);
                    System.out.println("\nВикладач " + dean.getFullName() + " буде деканом цього факультету.");
                    return dean;
            }
        }
    }

    private static String getContacts(){
        String contactForCommunication = InputReader.readLine("Введіть контакти: ", 1, 50);
        return contactForCommunication;
    }

    public static boolean printAll(List<Faculty> allFaculties){
        if(allFaculties.size()!=0){
            System.out.println("\nСПИСОК ФАКУЛЬТЕТІВ:");
            int count = 1;
            for(Faculty faculty : allFaculties){
                System.out.println(count + ". " + faculty);
                count++;
            }
            return true;
        }else{
            System.out.println("\nСписок факультетів порожній.");
        }
        return false;
    }

    private static void changeForm(){
        if(printAll(FacultyService.getAll())){
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор факультету, який хочете змінити: ", 7, 7);
            Optional<Faculty> maybeFaculty = FacultyService.findById(id);
            if(maybeFaculty.isPresent()){
                Faculty foundFaculty = maybeFaculty.get();
                while(true){
                    int whatToChange = InputReader.readInt("\nВиберіть, що хочете змінити:" +
                            "\n1 - повну назву" +
                            "\n2 - скорочену назву" +
                            "\n3 - декана" +
                            "\n4 - контакти" +
                            "\n0 - повернутись на крок назад", 0, 4);
                    switch (whatToChange){
                        // full name to change
                        case 1:
                            foundFaculty.setFullName(getFullName());
                            break;
                        // short name to change
                        case 2:
                            foundFaculty.setShortName(getShortName());
                            break;
                        // dean to change
                        case 3:
                            foundFaculty.getDean().setPosition(Position.TEACHER);
                            foundFaculty.setDean(getDean());
                            break;
                        // contacts for communication to change
                        case 4:
                            foundFaculty.setContactForCommunication(getContacts());
                            break;
                        // exit
                        case 0:
                            break;
                    }
                    if(whatToChange==0) break;
                    System.out.println("\nЗміни проведені успішно. Оновлені дані: \n" + foundFaculty);
                }
            }else{
                System.out.println("\nФакультету з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає що змінювати.");
        }
    }

    private static void deleteForm(){
        if(printAll(FacultyService.getAll())) {
            String id = InputReader.readLine("\nВведіть унікальний ідентифікатор факультету, який хочете видалити: ", 7, 7);
            Optional<Faculty> maybeFaculty = FacultyService.findById(id);
            if (maybeFaculty.isPresent()) {
                Faculty foundFaculty = maybeFaculty.get();
                System.out.println("\nУВАГА!!!");
                System.out.println("Після видалення факультету всі кафедри, студенти та викладачі, що приписані до цього факультету, більше не будуть до нього приписані.");
                int makingSure = InputReader.readInt("Введіть 1, якщо точно хочете видалити, або 0, щоби відмінити дію: ", 0, 1);
                if(makingSure==1){
                    FacultyService.delete(foundFaculty);
                    System.out.println("\nВи успішно видалили факультет " + foundFaculty.getFullName() + ". Оновлені дані: ");
                    printAll(FacultyService.getAll());
                }
            }else{
                System.out.println("\nФакультету з унікальним ідентифікатором " + id + " не знайдено.");
            }
        }else{
            System.out.println("Немає що видаляти.");
        }
    }

}
