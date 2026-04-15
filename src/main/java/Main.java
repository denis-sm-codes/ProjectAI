import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordReceaver brain = new WordReceaver();

        System.out.println("ИИ: Привет! Напиши мне что-нибудь.");

        while (true) {
            System.out.print("Вы: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("выход")) break;

            String response = brain.getFinalResponse(input);

            System.out.println("ИИ: " + response);
        }
    }
}