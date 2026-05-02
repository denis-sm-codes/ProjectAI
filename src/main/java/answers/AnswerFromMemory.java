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

        int choice = random.nextInt(4);

        if (choice == 0) {
            return "Мы тут недавно упоминали '" + original + "'. Это ведь как-то связано с темой '" + mapped + "', я прав?";
        } else if (choice == 1) {
            return "Слушай, а если вернуться к '" + original + "'... Ты считаешь, что это близко к понятию '" + mapped + "'?";
        } else if (choice == 2) {
            return "Мне всё не дает покоя слово '" + original + "'. Это же почти то же самое, что и '" + mapped + "'?";
        } else {
            return "Помнишь, ты говорил про '" + original + "'? Мне кажется, это отличный пример для темы '" + mapped + "'.";
        }
    }

    public String askAbout(String key) {
        List<String> templates = List.of(
                "Что для тебя означает '%s'?",
                "А что такое '%s' в твоем понимании?",
                "Мне интересно, а что ты подразумеваешь под словом '%s'?",
                "Никак не пойму, '%s' — это что?"
        );
        return String.format(templates.get(random.nextInt(templates.size())), key);
    }
}