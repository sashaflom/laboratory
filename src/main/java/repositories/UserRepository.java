package repositories;

import domain.Role;
import domain.Teacher;
import domain.User;
import domain.UserStatus;

import java.util.*;

public final class UserRepository implements Repository<User, String>{

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

    private User baseAdmin = new User("sashaFlom", "0123456789", Role.ADMINISTRATOR, UserStatus.PERMITTED);

    private Map<String, User> users = Map.of(baseAdmin.login(), baseAdmin);

    @Override
    public void add(User user){
        users.put(user.login(), user);
    }

    @Override
    public Optional<User> findById(String login){
        Optional<User> foundUser = Optional.ofNullable(users.get(login));
        return foundUser;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void delete(User user){
        users.remove(user.login());
    }

}
