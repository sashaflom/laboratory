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
}
