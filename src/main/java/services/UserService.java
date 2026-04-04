package services;

import domain.*;
import repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static UserRepository repository = UserRepository.getInstance();
    private static User sessionUser;
    private static Role sessionRole;

    public static Optional<User> findByLogin(String login){
        return repository.findById(login);
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

    public static User createNewAndAdd(String login, String password, Role role, UserStatus status){
        User user = new User(login, password, role, status);
        repository.add(user);
        return user;
    }

    public static List<User> getAll(){
        return repository.getAll();
    }

    public static void delete(User user){
        repository.delete(user);
    }
}
