package exceptions;

public class DuplicateIdException extends ValidationException {
    public DuplicateIdException(String message) {
        super(message);
    }
}
