package domain;

import java.time.Period;
import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Person {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private final String id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;

    public Person(String id, String lastname, String firstname, String patronymic,
                  LocalDate birthDate, String email, String phoneNumber) {
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
    public LocalDate getBirthDate() { return birthDate; }
    public String getEmail() { return email;}
    public String getPhoneNumber() { return phoneNumber;}

    public void setLastname(String Lastname) { this.lastName = Lastname;}
    public void setFirstname(String Firstname) { this.firstName = Firstname;}
    public void setPatronymic(String patronymic){this.patronymic = patronymic;}
    public void setBirthDate (LocalDate birthDate){this.birthDate = birthDate;}
    public void setEmail(String email){this.email=email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber=phoneNumber;}

    // method which will be useful
    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    @Override
    public String toString() {
        return String.format(
                        "ID: '%s', " +
                        "ПІБ: '%s %s %s', " +
                        "дата народження: '%s', " +
                        "вік: '%s', " +
                        "email: '%s', " +
                        "телефон: '%s'",
                id, lastName, firstName, patronymic, birthDate.format(FORMATTER), getHowOld(), email, phoneNumber);
    }

    public int getHowOld() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return (Objects.equals(id, person.id) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(patronymic, person.patronymic) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, lastName, firstName, patronymic, birthDate, email, phoneNumber);
    }
}