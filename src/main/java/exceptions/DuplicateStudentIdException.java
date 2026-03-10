package exceptions;

public class DuplicateStudentIdException extends ValidationException {
    public DuplicateStudentIdException(String message) {
        super(message);
    }
}
