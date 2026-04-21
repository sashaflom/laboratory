package domain;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

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

}
