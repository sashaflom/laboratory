package ui;

import data.DataService;
import domain.*;
import repositories.*;
import services.*;
import validators.InputReader;

import java.util.Optional;
import java.time.LocalDate;

public class MainMenu {

    public static void main(String[] args){
        University kyivMohylaAcademy = new University("Національний університет 'Києво-Могилянська академія'",
                "НаУКМА", "Київ", "вулиця Григорія Сковороди, 2, Київ, 04655");
        System.out.println("Вітаю в інформаційній системі Києво-Могилянської академії!");
        System.out.println("\nСтворено:" + kyivMohylaAcademy);

        System.out.println("\nАВТОРИЗАЦІЯ");
        while(true){
            String login = InputReader.readLine("Введіть логін: ", 5, 30);
            Optional<User> maybeUser = UserService.findByLogin(login);
            while(maybeUser.isEmpty()){
                System.out.println("\nПомилка! Такого користувача не знайдено, спробуйте ще раз.");
                login = InputReader.readLine("Введіть логін: ", 5, 30);
                maybeUser = UserService.findByLogin(login);
            }
            UserService.setSessionUser(maybeUser.get());
            int tryPassword = 3;
            while(tryPassword > 0){
                String password = InputReader.readLine("Введіть пароль: ", 10, 10);
                if(UserService.checkPassword(password)){
                    UserService.setSessionRole();
                    System.out.println("\nВи увійшли в акаунт " + UserService.getSessionUserLogin() + ". Ваша роль: " + UserService.getSessionRole());
                    break;
                }else{
                    tryPassword--;
                    if(tryPassword==0){
                        System.out.println("\nНевірний пароль! Ваші спроби закінчились.");
                    }else{
                        System.out.println("\nНевірний пароль! Залишилось спроб: " + tryPassword);
                    }
                }
            }
            if(tryPassword>0) break;
        }

        DataService.loadData();

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
                    if(!UserService.isUser()){
                        while(true){
                            int whatToWorkWith = InputReader.readInt("\nВиберіть, з чим хочете працювати: " +
                                    "\n1 - факультет" +
                                    "\n2 - кафедра" +
                                    "\n3 - студент" +
                                    "\n4 - викладач" +
                                    "\n0 - повернутись на крок назад", 0, 4);
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
                    DataService.saveData();
                    System.out.println("\nДякую, що прийшли!");
                    break;
            }
            if (whatToDo==0) break;
        }
    }

}
