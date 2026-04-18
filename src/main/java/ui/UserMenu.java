package ui;

import data.DataService;
import domain.*;
import exceptions.DuplicateIdException;
import services.UserService;
import validators.InputReader;
import validators.UserValidator;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMenu {

    private static final Logger logger = LoggerFactory.getLogger(UserMenu.class);

    public static void selectOperation(){
        while(true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити: " +
                    "\n1 - створити нового користувача" +
                    "\n2 - побачити всіх користувачів" +
                    "\n3 - редагувати існуючого користувача" +
                    "\n4 - видалити існуючого користувача" +
                    "\n5 - заблокувати існуючого користувача" +
                    "\n6 - розблокувати існуючого користувача" +
                    "\n0 - повернутись на крок назад", 0, 6);
            switch (whatToDo){
                // create a new one was chosen
                case 1:
                    int makingSure = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure==1){
                        createForm();
                    }
                    break;
                // see existing one was chosen
                case 2:
                    int makingSure2 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure2==1){
                        printAll(UserService.getAll());
                    }
                    break;
                // change existing one was chosen
                case 3:
                    int makingSure3 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure3==1){
                        changeForm();
                    }
                    break;
                // delete existing one was chosen
                case 4:
                    int makingSure4 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure4==1){
                        deleteForm();
                    }
                    break;
                // block existing one was chosen
                case 5:
                    int makingSure5 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure5==1){
                        block();
                    }
                    break;
                // restore from block existing one was chosen
                case 6:
                    int makingSure6 = InputReader.readInt("Введіть 1, щоби розпочати, або 0, щоби відмінити дію: ", 0, 1);
                    if(makingSure6==1){
                        unblock();
                    }
                    break;
                // exit was chosen
                case 0:
                    break;
            }
            if(whatToDo==0) break;
        }
    }

    public static void createForm(){
        System.out.println("\nВи успішно додали користувача: \n" + UserService.createNewAndAdd(getLogin(), getPassword(),
                getRole(), UserStatus.PERMITTED));
    }

    private static String getLogin(){
        String login = InputReader.readLine("Введіть логін: ", 5, 30);
        while(true){
            try{
                UserValidator.isLoginValid(login);
                logger.info("Логін {} валідний, він не зайнятий іншим користувачем", login);
                break;
            } catch (DuplicateIdException e) {
                logger.error("Помилка, уже існує користувач з логіном {}: {}", login, e.getMessage());
                System.out.println(e.getMessage());
            }
            login = InputReader.readLine("Введіть логін: ", 5, 30);
        }
        return login;
    }

    public static String getPassword(){
        String password = InputReader.readLine("Введіть пароль: ", 10, 30);
        return password;
    }

    private static Role getRole(){
        int indexOfRole = InputReader.readInt("Виберіть роль:" +
                "\n0 - Користувач" +
                "\n1 - Менеджер" +
                "\n2 - Адміністратор", 0, 2);
        return Role.values()[indexOfRole];
    }

    public static boolean printAll(List<User> allUsers){
        if(allUsers.size()!=0){
            System.out.println("\nСПИСОК КОРИСТУВАЧІВ:");
            int count = 1;
            for(User user : allUsers){
                System.out.println(count + ". " + user);
                count++;
            }
            return true;
        }else{
            System.out.println("\nСписок користувачів порожній.");
        }
        return false;
    }

    private static void deleteForm(){
        if(printAll(UserService.getAll())) {
            String login = InputReader.readLine("\nВведіть логін користувача, якого хочете видалити: ", 5, 30);
            Optional<User> maybeUser = UserService.findByLogin(login);
            if (maybeUser.isPresent()) {
                User foundUser = maybeUser.get();
                UserService.delete(foundUser);
                System.out.println("\nВи успішно видалили користувача. Оновлені дані: ");
                printAll(UserService.getAll());
            }else{
                System.out.println("\nКористувача з логіном " + login + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого видаляти.");
        }
    }

    private static void changeForm(){
        if(printAll(UserService.getAll())){
            String login = InputReader.readLine("\nВведіть логін користувача, якого хочете змінити: ", 5, 30);
            Optional<User> maybeUser = UserService.findByLogin(login);
            if(maybeUser.isPresent()){
                User foundUser = maybeUser.get();
                while(true){
                    int whatToChange = InputReader.readInt("\nВиберіть, що ви хочете змінити:" +
                            "\n1 - логін" +
                            "\n2 - пароль" +
                            "\n3 - роль" +
                            "\n0 - повернутись на крок назад", 0, 3);
                    switch (whatToChange){
                        // login name to change
                        case 1:
                            String newLogin = getLogin();
                            UserService.newLogin(login, newLogin);
                            break;
                        // password to change
                        case 2:
                            foundUser.setPassword(getPassword());
                            break;
                        // role to change
                        case 3:
                            foundUser.setRole(getRole());
                            UserService.setSessionRole();
                            break;
                        // exit
                        case 0:
                            break;
                    }
                    if(whatToChange==0) break;
                    System.out.println("\nЗміни проведені успішно. Оновлені дані: \n" + foundUser);
                }
            }else{
                System.out.println("\nКористувача з логіном " + login + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого змінювати.");
        }
    }

    private static void block(){
        if(printAll(UserService.getAll())){
            String login = InputReader.readLine("\nВведіть логін користувача, якого хочете заблокувати: ", 5, 30);
            Optional<User> maybeUser = UserService.findByLogin(login);
            if(maybeUser.isPresent()){
                User foundUser = maybeUser.get();
                if(foundUser.getStatus() == UserStatus.BLOCKED){
                    System.out.println("\nКористувач " + login + " уже заблокований.");
                } else{
                    foundUser.setStatus(UserStatus.BLOCKED);
                    System.out.println("\nВи успішно заблокували користувачва з логіном " + login + ".");
                }
            }else{
                System.out.println("\nКористувача з логіном " + login + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого блокувати.");
        }
    }

    private static void unblock(){
        if(printAll(UserService.getAll())){
            String login = InputReader.readLine("\nВведіть логін користувача, якого хочете розблокувати: ", 5, 30);
            Optional<User> maybeUser = UserService.findByLogin(login);
            if(maybeUser.isPresent()){
                User foundUser = maybeUser.get();
                if(foundUser.getStatus() == UserStatus.PERMITTED){
                    System.out.println("\nКористувач " + login + " уже розблокований.");
                } else {
                    foundUser.setStatus(UserStatus.PERMITTED);
                    System.out.println("\nВи успішно розблокували користувачва з логіном " + login + ".");
                }
            }else{
                System.out.println("\nКористувача з логіном " + login + " не знайдено.");
            }
        }else{
            System.out.println("Немає кого блокувати.");
        }
    }
}
