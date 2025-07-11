package Classes.Helpers;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public int getInt() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public String getString() {
        String input = scanner.nextLine();
        return input;
    }
}
