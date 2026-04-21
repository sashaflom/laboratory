package domain;

import java.io.Serializable;

public class University implements Serializable {

    private String fullName;
    private String shortName;
    private String city;
    private String address;

    public University(String fullName, String shortName, String city, String address){
        this.fullName = fullName;
        this.shortName = shortName;
        this.city = city;
        this.address = address;
    }

    @Override
    public String toString(){
        return "\nУніверситет: \nповна назва: '%s', \nскорочена назва: '%s', \nмісто: '%s', \nадреса: '%s'.".formatted(fullName, shortName, city, address);
    }

    public String getFullName() {
        return fullName;
    }
    public String getShortName() {
        return shortName;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
