package answers;

import java.util.List;
import java.util.Random;

public class AnswerFromMemory {
    private final Random random = new Random();
    private final Memory memory;
    public AnswerFromMemory(Memory memory) {
        this.memory = memory;
    }

    // ТЕПЕРЬ МЕТОД ПРИНИМАЕТ ПАРУ ИЗВНЕ
    public String generateFromHistory(SemanticPair pair) {
        if (pair == null) return null;

        String original = pair.getOriginalWord();
        String mapped = pair.getMappedWord();

        int choice = random.nextInt(6);

        if (choice == 0) {
            return "Мы говорили про " + original + ", это как то связанно с " + mapped + "?";
        } else if (choice == 1) {
            return "Ты упоминал " + original + ". Это тема похожа на " + mapped + "?";
        } else if (choice == 2) {
            return "Я помню ты упоминал " + original + ", это похоже на " + mapped + "?";
        } else if (choice == 3) {
            return "Что ещё ты можешь рассказать кроме " + original + " и " + mapped + "?";
        } else if (choice == 4) {
            return original + " это интересно так же как и " + mapped + ", не правда ли?";
        } else {
            return "Я запомнил что мы говорили про " + original + " и " + mapped;
        }
    }

    public String askAbout(String key) {
        List<String> templates = List.of(
                "Что для тебя означает " + key + "?",
                "А что такое " + key + " в твоем понимании?",
                "Мне интересно, а что ты подразумеваешь под словом " + key + "?",
                "Никак не пойму, " + key + " - это что?",
                "Что такое " + key + "?",
                "Расскажи мне про " + key,
                "Проясни мне про " + key
        );
        return templates.get(random.nextInt(templates.size()));
    }
}