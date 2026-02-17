package validators;

import repositories.FacultyRepository;

public class PersonValidator {

    public PersonValidator(){

    }

    public boolean isPhoneNumberValid(String phoneNumber){
        int index;
        if(phoneNumber.charAt(0) == '+'){
            index = 1;
        }else{
            index = 0;
        }
        for(int i = index; i<phoneNumber.length(); i++){
            if(phoneNumber.charAt(i)<'0' || phoneNumber.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

}
