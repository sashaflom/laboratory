package validators;

import domain.User;
import exceptions.DuplicateIdException;
import repositories.UserRepository;

import java.util.Optional;

public class UserValidator {

    private static UserRepository repository = UserRepository.getInstance();

    public static void isLoginValid(String login) throws DuplicateIdException {
        Optional<User> maybeUser = repository.findById(login);
        if(maybeUser.isPresent()){
            throw new DuplicateIdException("Помилка! Уже існує користувач з логіном " + login + ".");
        }
    }

}
