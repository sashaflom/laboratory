package validators;

public class PersonValidator {

    public static boolean isPhoneNumberValid(String phoneNumber){
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

    public static boolean isEmailValid(String email){
        int atSymbolIndex = -1;
        for(int i = 0; i<email.length(); i++){
            if(email.charAt(i) == '@'){
                atSymbolIndex = i;
            }
        }
        if(atSymbolIndex == -1){
            return false;
        }else{
            String afterAt = email.substring(atSymbolIndex);
            if(afterAt.equals("@ukma.edu.ua")){
                return true;
            }else{
                return false;
            }
        }
    }

}
