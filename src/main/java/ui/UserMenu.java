package ui;

import domain.*;
import exceptions.DuplicateIdException;
import services.TeacherService;
import services.UserService;
import validators.InputReader;
import validators.PersonValidator;
import validators.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserMenu {

    public static void selectOperation(){
        while(true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити: " +
                    "\n1 - створити нового користувача" +
                    "\n2 - побачити всіх користувачів" +
                    "\n3 - редагувати існуючого користувача" +
                    "\n4 - видалити існуючого користувача" +
                    "\n5 - заблокувати існуючого користувача" +
                    "\n0 - повернутись на крок назад", 0, 5);
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
                        // changeForm();
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
                        // deleteForm();
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
                break;
            } catch (DuplicateIdException e) {
                System.out.println(e.getMessage());
            }
            login = InputReader.readLine("Введіть логін: ", 5, 30);
        }
        return login;
    }

    private static String getPassword(){
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

}
