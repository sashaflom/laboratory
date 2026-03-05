package services;

import domain.User;
import repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private static UserRepository repository = UserRepository.getInstance();

    public static Optional<User> findByLogin(String login){
        return repository.findByLogin(login);
    }

    public static boolean checkPassword(String password, User user){
        return password.equals(user.getPassword());
    }

}
