package domain;

import java.util.Objects;

public class Faculty {

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
        return "Факультет: унікальний код: '%s', повна назва: '%s', скорочена назва: '%s', декан: '%s', контакт: '%s'.".formatted(uniqueCode, fullName, shortName, (dean != null ? dean.getFullName() : "не призначено"), contactForCommunication);
    }

    public String getUniqueCode() {
        return uniqueCode;
    }
    public String getFullName() {
        return fullName;
    }
    public String getShortName() {
        return shortName;
    }
    public Teacher getDean() {
        return dean;
    }
    public String getContactForCommunication() {
        return contactForCommunication;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public void setDean(Teacher dean) {
        this.dean = dean;
    }
    public void setContactForCommunication(String contactForCommunication) {
        this.contactForCommunication = contactForCommunication;
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return (Objects.equals(uniqueCode, faculty.uniqueCode) &&
                Objects.equals(fullName, faculty.fullName) &&
                Objects.equals(shortName, faculty.shortName) &&
                Objects.equals(dean.getId(), faculty.dean.getId()) &&
                Objects.equals(contactForCommunication, faculty.contactForCommunication));
    }

    @Override
    public int hashCode(){
        return Objects.hash(uniqueCode, fullName, shortName, dean.getId(),
                contactForCommunication);
    }
}
