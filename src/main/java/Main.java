import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {}
        }));
        Scanner scanner = new Scanner(System.in);
        FinalResponse brain = new FinalResponse();

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