package domain;

import annotations.NotBlank;
import annotations.Phone;
import annotations.UkmaEmail;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.time.Period;
import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Student.class, name = "student"),
        @JsonSubTypes.Type(value = Teacher.class, name = "teacher")
})
public class Person implements Serializable {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private final String id;
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @NotBlank
    private String patronymic;
    private LocalDate birthDate;
    @UkmaEmail
    private String email;
    @Phone
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

    public Person() {
        this.id = null;
    }

    public String getId() {return id;}
    public String getLastName() { return lastName;}
    public String getFirstName() { return firstName;}
    public String getPatronymic() { return patronymic;}
    public LocalDate getBirthDate() { return birthDate; }
    public String getEmail() { return email;}
    public String getPhoneNumber() { return phoneNumber;}

    public void setLastName(String Lastname) { this.lastName = Lastname;}
    public void setFirstName(String Firstname) { this.firstName = Firstname;}
    public void setPatronymic(String patronymic){this.patronymic = patronymic;}
    public void setBirthDate (LocalDate birthDate){this.birthDate = birthDate;}
    public void setEmail(String email){this.email=email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber=phoneNumber;}

    // method which will be useful
    @JsonIgnore
    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }

    @Override
    public String toString() {
        return String.format(
                        "ПІБ: '%s %s %s', " +
                        "ID: '%s', " +
                        "дата народження: '%s', " +
                        "вік: '%s', " +
                        "email: '%s', " +
                        "телефон: '%s'",
                lastName, firstName, patronymic, id, birthDate.format(FORMATTER), getHowOld(), email, phoneNumber);
    }

    @JsonIgnore
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