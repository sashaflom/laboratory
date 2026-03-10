package exceptions;

public class DuplicateNameException extends ValidationException {
    public DuplicateNameException(String message) {
        super(message);
    }
}
