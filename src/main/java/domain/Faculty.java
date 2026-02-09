package domain;

import repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Faculty {

    private static Scanner scanner = new Scanner(System.in);

    private final String uniqueCode;
    private String fullName;
    private String shortName;
    private Teacher dean;
    private String contactForCommunication;

    public Faculty(String uniqueCode, String fullName, String shortName, Teacher dean, String contactForCommunication){
        this.uniqueCode = uniqueCode;
        this.fullName = fullName;
        this.shortName = shortName;
        this.dean = dean;
        this.contactForCommunication = contactForCommunication;
    }

    @Override
    public String toString(){
        return "Факультет: унікальний код: '%s', повна назва: '%s', скорочена назва: '%s', декан: '%s', контакт: '%s'.".formatted(uniqueCode, fullName, shortName, dean, contactForCommunication);
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Teacher getDean() {
        return dean;
    }

    public void setDean(Teacher dean) {
        this.dean = dean;
    }

    public String getContactForCommunication() {
        return contactForCommunication;
    }

    public void setContactForCommunication(String contactForCommunication) {
        this.contactForCommunication = contactForCommunication;
    }

    public static void selectOperation(){
        while(true){
            System.out.println("Виберіть, що хочете зробити: " +
                    "\n1 - створити новий" +
                    "\n2 - побачити всі факультети" +
                    "\n3 - редагувати існуючий" +
                    "\n4 - видалити існуючий" +
                    "\n0 - вийти на рівень вище");
            int whatToDo;
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            whatToDo = scanner.nextInt();
            while(whatToDo<0 || whatToDo>4){
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                }
                whatToDo = scanner.nextInt();
            }

            // create a new one was chosen
            if(whatToDo==1){
                createNew();
                // see existing one was chosen
            }else if (whatToDo==2){
                System.out.println("СПИСОК ФАКУЛЬТЕТІВ:");
                Repository.getInstance().showAllFaculties();
                // change existing one was chosen
            }else if(whatToDo==3){
                Repository.getInstance().changeFaculty();
                // delete existing one was chosen
            }else if (whatToDo==4){
                Repository.getInstance().deleteFaculty();
                // exit was chosen
            }else if (whatToDo==0){
                break;
            }
        }
    }

    private static void createNew(){
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
            Repository.getInstance().addFaculty(new Faculty(uniqueCodeValidation(),
                    fullNameValidation(),
                    shortNameValidation(),
                    deanValidation(),
                    contactForCommunicationValidation()));
            System.out.println("Ви успішно додали факультет: \n" + Repository.getInstance().findLastAddedFaculty());
        }
    }

    private static String uniqueCodeValidation(){
        scanner.nextLine();
        System.out.println("Введіть унікальний код з 7 знаків: ");
        String uniCode = scanner.nextLine();
        while(uniCode.length() != 7){
            System.out.println("Не може бути такої довжини, введіть 7 знаків: ");
            uniCode = scanner.nextLine();
        }
        List<Faculty> faculties = Repository.getInstance().getFaculties();
        if(faculties.size() != 0){
            for(int i=0; i<faculties.size(); i++){
                while(faculties.get(i).getUniqueCode().equals(uniCode)){
                    System.out.println("Факультет з таким кодом уже існує, введіть інший:");
                    uniCode = scanner.nextLine();
                    while(uniCode.length() != 7) {
                        System.out.println("Не може бути такої довжини, введіть 7 знаків: ");
                        uniCode = scanner.nextLine();
                    }
                }
            }
        }
        return uniCode;
    }

    public static String fullNameValidation(){
        scanner.nextLine();
        System.out.println("Введіть повну назву: ");
        String fName = scanner.nextLine();
        while(fName.equals("") || fName.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть повну назву: ");
            fName = scanner.nextLine();
        }
        List<Faculty> faculties = Repository.getInstance().getFaculties();
        if(faculties.size() != 0){
            for(int i=0; i<faculties.size(); i++){
                while(faculties.get(i).getFullName().equals(fName)){
                    System.out.println("Факультет з такою назвою вже існує, введіть іншу:");
                    fName = scanner.nextLine();
                    while(fName.equals("") || fName.equals(" ")){
                        System.out.println("Не може бути порожній рядок, введіть повну назву: ");
                        fName = scanner.nextLine();
                    }
                }
            }
        }
        return fName;
    }

    public static String shortNameValidation(){
        scanner.nextLine();
        System.out.println("Введіть скорочену назву: ");
        String shName = scanner.nextLine();
        while(shName.equals("") || shName.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть скорочену назву: ");
            shName = scanner.nextLine();
        }
        List<Faculty> faculties = Repository.getInstance().getFaculties();
        if(faculties.size() != 0){
            for(int i=0; i<faculties.size(); i++){
                while(faculties.get(i).getShortName().equals(shName)){
                    System.out.println("Факультет з такою назвою вже існує, введіть іншу:");
                    shName = scanner.nextLine();
                    while(shName.equals("") || shName.equals(" ")){
                        System.out.println("Не може бути порожній рядок, введіть скорочену назву: ");
                        shName = scanner.nextLine();
                    }
                }
            }
        }
        return shName;
    }

    public static Teacher deanValidation(){
        while (true){
            System.out.println("Виберіть, як хочете назначити декана: " +
                    "\n1 - вибрати з уже існуючих викладачів" +
                    "\n2 - створити нового викладача та назначити деканом");
            int howToChooseDean;
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            howToChooseDean = scanner.nextInt();
            while(howToChooseDean!=1 && howToChooseDean!=2){
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                }
                howToChooseDean = scanner.nextInt();
            }

            //choose from existing
            if(howToChooseDean==1){
                System.out.println("СПИСОК НАЯВНИХ ВИКЛАДАЧІВ: ");
                Repository.getInstance().showAllTeachers();
                if(Repository.getInstance().getTeachers().size()!=0){
                    System.out.println("Введіть ID викладача, якого хочете назначити деканом: ");
                    scanner.nextLine();
                    String id = scanner.nextLine();
                    while (id.length() != 5) {
                        System.out.println("Не може бути такої довжини, введіть 5 знаків: ");
                        id = scanner.nextLine();
                    }
                    Optional<Teacher> maybeTeacher = Repository.getInstance().findTeacherByID(id);
                    if(maybeTeacher.isPresent()){
                        System.out.println("Цей викладач буде деканом");
                        return maybeTeacher.get();
                    }else{
                        System.out.println("Немає викладача з таким ID.");
                    }
                }else{
                    System.out.println("Не створено жодного викладача, отже ви не можете вибрати");
                }
                // create new
            }else if(howToChooseDean==2){
                Teacher.createNew();
                System.out.println("Цей викладач буде деканом");
                return Repository.getInstance().findLastAddedTeacher();
            }
        }
    }

    public static String contactForCommunicationValidation(){
        scanner.nextLine();
        System.out.println("Введіть контакти: ");
        String contacts = scanner.nextLine();
        while(contacts.equals("") || contacts.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть контакти: ");
            contacts = scanner.nextLine();
        }
        return contacts;
    }

}
