package services;

import domain.Role;
import domain.User;
import repositories.UserRepository;

import java.util.Optional;

public class UserService {

    private static UserRepository repository = UserRepository.getInstance();
    private static User sessionUser;
    private static Role sessionRole;

    public static Optional<User> findByLogin(String login){
        return repository.findByLogin(login);
    }

    public static boolean checkPassword(String password){
        if(password.equals(sessionUser.getPassword())){
            sessionRole = sessionUser.getRole();
            return true;
        }
        return false;
    }

    public static void setSessionUser(User sessionUser) {
        UserService.sessionUser = sessionUser;
    }

    public static String getSessionUserLogin() {
        return sessionUser.getLogin();
    }

    public static String getSessionRole() {
        return sessionRole.getDisplayName();
    }

    public static boolean isUser() {
        return sessionRole == Role.USER;
    }

    public static void setSessionRole() {
        sessionRole = sessionUser.getRole();
    }
}
