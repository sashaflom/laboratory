package repositories;

import domain.Role;
import domain.User;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class UserRepository {

    // static class variable that will store a reference to a single instance of the class
    private static UserRepository instance = null;

    // the constructor is private so that it is impossible to create an instance of the class anywhere outside
    private UserRepository(){}

    // a static class method that should return a reference to a single instance of the class
    public static UserRepository getInstance(){
        // if it's null, then this is the first call to the method and you need to create a new instance of the class
        if (instance == null){
            // create and write it to a static class variable
            instance = new UserRepository();
            /*
            after that, in subsequent calls to getInstance we will never create a new
            instance, but will always return the one created in the very first call
             */
        }
        // return value
        return instance;
    }

    private User admin = new User("sashaFlom", "0123456789", Role.ADMINISTRATOR);
    private User manager = new User("sflom", "1029384756", Role.MANAGER);
    private User user = new User("flombik", "5647382910", Role.USER);

    private Map<String, User> users = Map.of(admin.getLogin(), admin, manager.getLogin(), manager,
            user.getLogin(), user);

    public Optional<User> findByLogin(String login){
        Optional<User> foundUser = Optional.ofNullable(users.get(login));
        return foundUser;
    }

}
