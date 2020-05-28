import java.util.InputMismatchException;
import java.util.Scanner;

public class InputMismatchExceptionDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueInput = true;

        while (continueInput){
            try {
                System.out.print("Enter an integer: ");
                int number = input.nextInt();
                System.out.println(
                        "The number entered is " + number);
                continueInput = false;

            }catch (InputMismatchException e){
                System.out.println();
                System.err.println("Try again. (" +
                        "Incorrect input: an integer is required)");
                input.nextLine();
            }
        }
    }
}
