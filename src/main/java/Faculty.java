public class Faculty {

    private final String uniqueCode;
    private String fullName;
    private String shortName;
    // here must be private Teacher dean;
    private String contactForCommunication;

    // DON'T FORGET TO ADD DEAN
    public Faculty(String uniqueCode, String fullName, String shortName, String contactForCommunication){
        this.uniqueCode = uniqueCode;
        this.fullName = fullName;
        this.shortName = shortName;
        this.contactForCommunication = contactForCommunication;
    }

    // DON'T FORGET TO ADD DEAN
    @Override
    public String toString(){
        return "Факультет: унікальний код: '%s', повна назва: '%s', скорочена назва: '%s', контакт: '%s'.".formatted(uniqueCode, fullName, shortName, contactForCommunication);
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

    public String getContactForCommunication() {
        return contactForCommunication;
    }

    public void setContactForCommunication(String contactForCommunication) {
        this.contactForCommunication = contactForCommunication;
    }
}
