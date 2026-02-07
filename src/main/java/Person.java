public class Person {

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
}