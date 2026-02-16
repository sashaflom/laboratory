package ui;

import domain.*;
import repositories.Repository;
import validators.InputReader;

import java.util.Scanner;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        University kyivMohylaAcademy = new University("Національний університет 'Києво-Могилянська академія'",
                "НаУКМА", "Київ", "вулиця Григорія Сковороди, 2, Київ, 04655");
        System.out.println("Вітаю в інформаційній системі Києво-Могилянської академії!");
        System.out.println("\nСтворено:" + kyivMohylaAcademy);

        while (true){
            int whatToDo = InputReader.readInt("\nВиберіть, що хочете зробити:" +
                    "\n1 - операції керування даними" +
                    "\n2 - пошук" +
                    "\n3 - звіти" +
                    "\n0 - закінчити", 0, 3);
            switch (whatToDo){
                // data management operations was chosen
                case 1:
                    while(true){
                        int whatToWorkWith = InputReader.readInt("\nВиберіть, з чим хочете працювати: " +
                                "\n1 - факультет" +
                                "\n2 - кафедра" +
                                "\n3 - студент" +
                                "\n4 - викладач" +
                                "\n0 - вийти на рівень вище", 0, 4);
                        switch (whatToWorkWith){
                            // faculty was chosen
                            case 1:
                                Faculty.selectOperation();
                                break;
                            // department was chosen
                            case 2:
                                Department.selectDepartmentOperation();
                                break;
                            // student was chosen
                            case 3:
                                Student.selectStudentOperation();
                                break;
                            // teacher was chosen
                            case 4:
                                Teacher.selectOperation();
                                break;
                            // exit was chosen
                            case 0:
                                break;
                        }
                        if(whatToWorkWith==0) break;
                    }
                    break;
                // search was chosen
                case 2:
                    while(true){
                        int whatToFind = InputReader.readInt("\nВиберіть, кого хочете знайти: " +
                                "\n1 - студента" +
                                "\n2 - викладача" +
                                "\n0 - вийти на рівень вище", 0, 2);
                        switch (whatToFind){
                            //find student was chosen
                            case 1:
                                Repository.findStudent();
                                break;
                            // find teacher was chosen
                            case 2:
                                Teacher.find();
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

                    break;
                // exit was chosen
                case 0:
                    System.out.println("Дякую, що прийшли!");
                    break;
            }
            if (whatToDo==0) break;
        }
    }

}
