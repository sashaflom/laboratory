package validators;

import java.util.Scanner;

public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message, int min, int max){
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                int choice = scanner.nextInt();
                scanner.nextLine();
                if(choice >= min && choice <= max){
                    return choice;
                }
            }else{
                scanner.next();
            }
            System.out.println("Немає такої опції, потрібно число від " + min + " до " + max + ".");
        }
    }

}
