package answers;

import java.util.Random;

public class AnswerForVerb {
    private final Random random = new Random();

    public String generate(String verb) {
        int choice = random.nextInt(4); // Выбираем из 4 вариантов

        if (choice == 0) {
            return "О, ты любишь " + verb + "? Расскажи, почему это тебе нравится?";
        } else if (choice == 1) {
            return "Мне кажется, " + verb + " — это отличное времяпровождение!";
        } else if (choice == 2) {
            return "А как часто ты стараешься " + verb + "?";
        } else {
            return "Интересно... Я бы тоже хотел попробовать " + verb + ".";
        }
    }
}