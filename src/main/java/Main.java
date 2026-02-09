import domain.University;
import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;

import java.util.Scanner;

public class Main {

    private static University kyivMohylaAcademy;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        kyivMohylaAcademy = new University("Національний університет 'Києво-Могилянська академія'",
                "НаУКМА", "Київ", "вулиця Григорія Сковороди, 2, Київ, 04655");
        System.out.println("Вітаю в інформаційній системі Києво-Могилянської академії!");
        System.out.println("\nСтворено:" + kyivMohylaAcademy);

        while (true){
            System.out.println("Виберіть, що хочете зробити:" +
                    "\n1 - операції керування даними" +
                    "\n2 - пошук" +
                    "\n3 - звіти" +
                    "\n0 - закінчити");
            int whatToDo;
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
            }
            whatToDo = scanner.nextInt();
            while(whatToDo<0 || whatToDo>3){
                System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                }
                whatToDo = scanner.nextInt();
            }

            // data management operations was chosen
            if (whatToDo==1){
                while(true){
                    System.out.println("Виберіть, з чим хочете працювати: " +
                            "\n1 - факультет" +
                            "\n2 - кафедра" +
                            "\n3 - студент" +
                            "\n4 - викладач" +
                            "\n0 - вийти на рівень вище");
                    int whatToWorkWith;
                    while (!scanner.hasNextInt()) {
                        String input = scanner.next();
                        System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                    }
                    whatToWorkWith = scanner.nextInt();
                    while(whatToWorkWith<0 || whatToWorkWith>4){
                        System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Немає такої опції, введіть число відповідно до інструкції: ");
                        }
                        whatToWorkWith = scanner.nextInt();
                    }

                    // faculty was chosen
                    if (whatToWorkWith==1){
                        Faculty.selectOperation();
                        // department was chosen
                    }else if(whatToWorkWith==2){

                        // student was chosen
                    }else if (whatToWorkWith==3){
                        Student.selectStudentOperation();
                        // teacher was chosen
                    }else if(whatToWorkWith==4){
                        Teacher.selectOperation();
                        // exit was chosen
                    }else if(whatToWorkWith==0){
                        break;
                    }
                }
                // search was chosen
            }else if(whatToDo==2){

                // reports was chosen
            }else if (whatToDo==3){

                // exit was chosen
            }else if(whatToDo==0){
                System.out.println("Дякую, що прийшли!");
                break;
            }
        }

    }

}
