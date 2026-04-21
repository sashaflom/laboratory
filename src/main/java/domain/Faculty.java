package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Objects;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Faculty implements Serializable {

    private final String id;
    private String fullName;
    private String shortName;
    @JsonIdentityReference(alwaysAsId = true)
    private Teacher dean;
    private String contactForCommunication;

    public Faculty(String id, String fullName, String shortName, Teacher dean, String contactForCommunication){
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.dean = dean;
        this.contactForCommunication = contactForCommunication;
    }

    public Faculty() {
        this.id = null;
    }

    @Override
    public String toString(){
        return "Факультет: ID: '%s', повна назва: '%s', скорочена назва: '%s', декан: '%s', контакт: '%s'.".formatted(id, fullName, shortName, (dean != null ? dean.getFullName() : "не призначено"), contactForCommunication);
    }

    public String getId() {
        return id;
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
    public String getContactForCommunication() {return contactForCommunication;}

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
        boolean partFields = (Objects.equals(id, faculty.id) &&
                Objects.equals(fullName, faculty.fullName) &&
                Objects.equals(shortName, faculty.shortName) &&
                Objects.equals(contactForCommunication, faculty.contactForCommunication));
        if ((dean != null && faculty.dean == null) || (dean == null && faculty.dean != null)){
            return false;
        }
        if (dean == null && faculty.dean == null){
            return partFields;
        }
        return partFields && Objects.equals(dean.getId(), faculty.dean.getId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, fullName, shortName, (dean != null ? dean.getId() : null),
                contactForCommunication);
    }
}
