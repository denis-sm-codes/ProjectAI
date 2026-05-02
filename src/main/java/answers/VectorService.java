package answers;

import java.util.*;

public class VectorService {
    private final Random random = new Random();

    public String findMeanWord(List<String> allWords) {
        if (allWords == null || allWords.isEmpty()) {
            return "это"; // Заглушка на случай полной пустоты
        }

        // Если слов несколько, мы выбираем ассоциацию из самой фразы пользователя.
        // Например, если фраза "Быстрый бег", и ключ "бег", то значением станет "быстрый".
        if (allWords.size() > 1) {
            // Перемешиваем список, чтобы ассоциация всегда была разной
            List<String> shuffleWords = new ArrayList<>(allWords);
            Collections.shuffle(shuffleWords);

            return shuffleWords.get(0);
        }

        // Если слово всего одно (нет контекста), мы генерируем "лингвистическое эхо"
        // Бот как бы расширяет понятие, делая его более абстрактным.
        return generateEcho(allWords.get(0));
    }

    private String generateEcho(String word) {
        List<String> abstractExtensions = List.of(
                "явление", "суть", "понятие", "смысл", "контекст", "объект"
        );

        // В 50% случаев возвращаем само слово, в 50% добавляем абстракцию
        if (random.nextBoolean()) {
            return word;
        } else {
            return abstractExtensions.get(random.nextInt(abstractExtensions.size()));
        }
    }
}