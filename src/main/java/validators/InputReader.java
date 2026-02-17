package validators;

import java.util.Scanner;

public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message, int min, int max){
        while (true){
            System.out.println(message);
            if (scanner.hasNextInt()){
                int input = scanner.nextInt();
                scanner.nextLine();
                if(input >= min && input <= max){
                    return input;
                }
            }else{
                scanner.next();
            }
            System.out.println("Помилка! Потрібно число від " + min + " до " + max + ".");
        }
    }

    public static String readLine(String message, int min, int max){
        while(true){
            System.out.println(message);
            String input = scanner.nextLine();
            if(input.length()>=min && input.length()<=max){
                return input;
            }
            if(min==max){
                System.out.println("Помилка! Довжина має бути " + min + " знаків.");
            }else{
                System.out.println("Помилка! Довжина має бути від " + min + " до " + max + " знаків.");
            }
        }
    }

    public static double readDouble(String message, int min, int max){
        while (true){
            System.out.println(message);
            if (scanner.hasNextDouble()){
                double input = scanner.nextDouble();
                scanner.nextLine();
                if(input >= min && input <= max){
                    return input;
                }
            }else{
                scanner.next();
            }
            System.out.println("Помилка! Потрібно число від " + min + " до " + max + ".");
        }
    }

}
