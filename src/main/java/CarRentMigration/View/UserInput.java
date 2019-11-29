package CarRentMigration.View;

import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static int getUserChoice() {
        String userInput = scanner.nextLine();
        if (userInput.isEmpty()) {
            System.out.println("Incorrect input");
            return 0;
        }
        String userChoice = userInput.replaceAll("[^0-9]", "");
        if (!userChoice.equals(userInput.trim())) {
            System.out.println("Incorrect input.");
            return 0;
        }
        return Integer.parseInt(userChoice);
    }

    public static String getUserString() {
        return scanner.nextLine();
    }
}
