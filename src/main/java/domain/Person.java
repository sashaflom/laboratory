package domain;

import java.util.Objects;
import java.util.Scanner;

public class Person {
    private static Scanner scanner = new Scanner(System.in);

    private final String id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String birthDate;
    private String email;
    private String phoneNumber;

    public Person(String id, String lastname, String firstname, String patronymic,
                  String birthDate, String email, String phoneNumber) {
        this.id = id;
        this.lastName = lastname;
        this.firstName = firstname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {return id;}
    public String getLastname() { return lastName;}
    public String getFirstname() { return firstName;}
    public String getPatronymic() { return patronymic;}
    public String getBirthDate() { return birthDate;}
    public String getEmail() { return email;}
    public String getPhoneNumber() { return phoneNumber;}

    public void setLastname(String Lastname) { this.lastName = Lastname;}
    public void setFirstname(String Firstname) { this.firstName = Firstname;}
    public void setPatronymic(String patronymic){this.patronymic = patronymic;}
    public void setBirthDate (String birthDate){this.birthDate=birthDate;}
    public void setEmail(String email){this.email=email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber=phoneNumber;}

    // method which will be useful
    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    @Override
    public String toString() {
        return String.format(
                        "ID: %s, " +
                        "ПІБ: %s %s %s, " +
                        "Дата народження: %s, " +
                        "Email: %s, " +
                        "Телефон: %s",
                id, lastName, firstName, patronymic, birthDate, email, phoneNumber);
    }

    public static String lastNameValidation(){
        scanner.nextLine();
        System.out.println("Введіть прізвище: ");
        String lastName = scanner.nextLine();
        while(lastName.equals("") || lastName.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть прізвище: ");
            lastName = scanner.nextLine();
        }
        return lastName;
    }

    public static String firstNameValidation(){
        scanner.nextLine();
        System.out.println("Введіть ім'я: ");
        String firstName = scanner.nextLine();
        while(firstName.equals("") || firstName.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть ім'я: ");
            firstName = scanner.nextLine();
        }
        return firstName;
    }

    public static String patronymicValidation(){
        scanner.nextLine();
        System.out.println("Введіть побатькові: ");
        String patronymic = scanner.nextLine();
        while(patronymic.equals("") || patronymic.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть побатькові: ");
            patronymic = scanner.nextLine();
        }
        return patronymic;
    }

    public static String birthDateValidation(){
        scanner.nextLine();
        System.out.println("Введіть рік народження: ");
        int bdYear;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий рік народження, введіть інший: ");
        }
        bdYear = scanner.nextInt();
        while(bdYear<1900 || bdYear>2026){
            System.out.println("Не може бути такий рік народження, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий рік народження, введіть інший: ");
            }
            bdYear = scanner.nextInt();
        }

        System.out.println("Введіть місяць народження цифрою: ");
        int bdMonth;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий місяць народження, введіть інший: ");
        }
        bdMonth = scanner.nextInt();
        while(bdMonth<1 || bdMonth>12){
            System.out.println("Не може бути такий місяць народження, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий місяць народження, введіть інший: ");
            }
            bdMonth = scanner.nextInt();
        }

        System.out.println("Введіть день народження цифрою: ");
        int bdDay;
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Не може бути такий день народження, введіть інший: ");
        }
        bdDay = scanner.nextInt();
        while(bdDay<1 || bdDay>31){
            System.out.println("Не може бути такий день народження, введіть інший: ");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Не може бути такий день народження, введіть інший: ");
            }
            bdDay = scanner.nextInt();
        }
        String birthDate = bdDay + "." + bdMonth + "." + bdYear;
        return birthDate;
    }

    public static String emailValidation(){
        scanner.nextLine();
        System.out.println("Введіть email: ");
        String email = scanner.nextLine();
        while(email.equals("") || email.equals(" ")){
            System.out.println("Не може бути порожній рядок, введіть email: ");
            email = scanner.nextLine();
        }
        return email;
    }

    public static String phoneNumberValidation(){
        scanner.nextLine();
        System.out.println("Введіть номер телефону: ");
        String phoneNumber = null;
        boolean everythingIsFine = false;
        while(!everythingIsFine){
            phoneNumber = scanner.nextLine();
            while(phoneNumber.length()!=13 && phoneNumber.length()!=12 && phoneNumber.length()!=10){
                System.out.println("Не може бути такий номер телефону, введіть інший: ");
                phoneNumber = scanner.nextLine();
            }
            int index;
            if(phoneNumber.charAt(0) == '+'){
                index = 1;
            }else{
                index = 0;
            }
            for(int i = index; i<phoneNumber.length(); i++){
                if(phoneNumber.charAt(i)<'0' || phoneNumber.charAt(i)>'9'){
                    System.out.println("Не може бути такий номер телефону, введіть інший: ");
                    break;
                }
                if (i==phoneNumber.length()-1){
                    everythingIsFine = true;
                }
            }
        }
        return phoneNumber;
    }
}