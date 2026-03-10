package exceptions;

public class InvalidPhoneNumber extends ValidationException {
    public InvalidPhoneNumber(String message) {
        super(message);
    }
}
