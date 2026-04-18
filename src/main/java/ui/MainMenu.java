package ui;

import data.DataService;
import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.*;
import services.*;
import validators.InputReader;

import java.util.Optional;
import java.time.LocalDate;

public class MainMenu {

    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);

    public static void main(String[] args){
        University kyivMohylaAcademy = new University("Національний університет 'Києво-Могилянська академія'",
                "НаУКМА", "Київ", "вулиця Григорія Сковороди, 2, Київ, 04655");
        System.out.println("Вітаю в інформаційній системі Києво-Могилянської академії!");
        System.out.println("\nСтворено:" + kyivMohylaAcademy);

        DataService.loadData();
        UserService.setUpUser();

        System.out.println("\nАВТОРИЗАЦІЯ");
        while(true){
            String login = InputReader.readLine("Введіть логін: ", 5, 30);
            Optional<User> maybeUser = UserService.findByLogin(login);
            while(maybeUser.isEmpty()){
                System.out.println("\nПомилка! Користувача з логіном " + login + " не знайдено, спробуйте ще раз.");
                login = InputReader.readLine("Введіть логін: ", 5, 30);
                maybeUser = UserService.findByLogin(login);
            }
            User foundUser = maybeUser.get();
            if(foundUser.isBlocked()){
                System.out.println("\nКористувач з логіном " + login + " заблокований, Ви не можете увійти в акаунт.");
            } else{
                UserService.setSessionUser(foundUser);
                int tryPassword = 3;
                while(tryPassword > 0){
                    String password = UserMenu.getPassword();
                    try {
                        UserService.checkPassword(password);
                        UserService.setSessionRole();
                        System.out.println("\nВи увійшли в акаунт " + UserService.getSessionUserLogin() + ". Ваша роль: " + UserService.getSessionRole());
                        logger.info("Авторизація в акаунт {} успішна", foundUser);
                        break;
                    } catch (IllegalAccessException e){
                        tryPassword--;
                        System.out.println(e.getMessage());
                        if(tryPassword==0){
                            logger.error("Вичерпані всі спроби увійти в акаунт {}, користувача заблоковано: {}", foundUser, e.getMessage());
                            System.out.println("\nВаші спроби закінчились.");
                            UserService.addToBlocked(foundUser);
                            System.out.println("\nКористувач з логіном " + login + " буде заблокований на цю сесію.");
                        }else{
                            logger.warn("Спроба увійти в акаунт {} з некоректним паролем: {}", foundUser, e.getMessage());
                            System.out.println("\nЗалишилось спроб: " + tryPassword);
                        }
                    }
                }
                if(tryPassword>0) break;
            }
        }

        AutoSaveService autoSaveService = new AutoSaveService();
        autoSaveService.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            autoSaveService.stop();
            DataService.saveData();
        }));

        while (true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити:" +
                    "\n1 - операції керування даними" +
                    "\n2 - пошук" +
                    "\n3 - звіти" +
                    "\n0 - закінчити", 0, 3);
            switch (whatToDo){
                // data management operations was chosen
                case 1:
                    if(UserService.has(Access.WRITE_DOMAIN)){
                        while(true){
                            int whatToWorkWith = InputReader.readInt("\nВиберіть, з чим хочете працювати: " +
                                    "\n1 - факультет" +
                                    "\n2 - кафедра" +
                                    "\n3 - студент" +
                                    "\n4 - викладач" +
                                    "\n5 - користувач" +
                                    "\n0 - повернутись на крок назад", 0, 5);
                            switch (whatToWorkWith){
                                // faculty was chosen
                                case 1:
                                    FacultyMenu.selectOperation();
                                    break;
                                // department was chosen
                                case 2:
                                    DepartmentMenu.selectOperation();
                                    break;
                                // student was chosen
                                case 3:
                                    StudentMenu.selectOperation();
                                    break;
                                // teacher was chosen
                                case 4:
                                    TeacherMenu.selectOperation();
                                    break;
                                // user was chosen
                                case 5:
                                    if(UserService.has(Access.WRITE_ALL)){
                                        UserMenu.selectOperation();
                                    }else{
                                        System.out.println("\nЦя дія недоступна для ролі " + UserService.getSessionRole() + ".");
                                    }
                                    break;
                                // exit was chosen
                                case 0:
                                    break;
                            }
                            if(whatToWorkWith==0) break;
                        }
                    }else{
                        System.out.println("\nЦя дія недоступна для ролі " + UserService.getSessionRole() + ".");
                    }
                    break;
                // search was chosen
                case 2:
                    while(true){
                        int whatToFind = InputReader.readInt("\nВиберіть, кого хочете знайти: " +
                                "\n1 - студента" +
                                "\n2 - викладача" +
                                "\n0 - повернутись на крок назад", 0, 2);
                        switch (whatToFind){
                            //find student was chosen
                            case 1:
                                StudentMenu.find();
                                break;
                            // find teacher was chosen
                            case 2:
                                TeacherMenu.find();
                                break;
                            //exit was chosen
                            case 0:
                                break;
                        }
                        if(whatToFind==0) break;
                    }
                    break;
                // reports was chosen
                case 3:
                    while(true){
                        int whatToShow = InputReader.readInt("\nВиберіть, які звіти хочете побачити: " +
                                "\n1 - студентів" +
                                "\n2 - викладачів" +
                                "\n0 - повернутись на крок назад", 0, 2);
                        switch (whatToShow){
                            //show student was chosen
                            case 1:
                                StudentMenu.report();
                                break;
                            //show teacher was chosen
                            case 2:
                                TeacherMenu.report();
                                break;
                            //exit was chosen
                            case 0:
                                break;
                        }
                        if(whatToShow==0) break;
                    }
                    break;
                // exit was chosen
                case 0:
                    autoSaveService.stop();
                    UserService.reblockAfterSession();
                    DataService.saveData();
                    System.out.println("\nДякую, що прийшли!");
                    break;
            }
            if (whatToDo==0) break;
        }
    }

}
