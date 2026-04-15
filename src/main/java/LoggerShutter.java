import java.io.OutputStream;
import java.io.PrintStream;

public class LoggerShutter {
    private static final PrintStream originalErr = System.err;
    private static final PrintStream silence = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) {}
    });

    // Метод для включения тишины
    public static void startSilence() {
        System.setErr(silence);
    }

    // Метод для возврата стандартного вывода
    public static void stopSilence() {
        System.setErr(originalErr);
    }
}