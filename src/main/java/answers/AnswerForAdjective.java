package answers;

import java.util.Random;

public class AnswerForAdjective {
    private final Random random = new Random();

    public String generate(String adj) {
        // Делаем первую букву заглавной для красоты (например, "синий" -> "Синий")
        String capAdj = adj.substring(0, 1).toUpperCase() + adj.substring(1);

        int choice = random.nextInt(3);

        if (choice == 0) {
            return capAdj + "? Это довольно точное описание, не находишь?";
        } else if (choice == 1) {
            return "Почему ты считаешь, что это именно " + adj + "?";
        } else {
            return "Хм, " + adj + "... Звучит очень необычно.";
        }
    }
}