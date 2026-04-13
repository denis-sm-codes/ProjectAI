import org.apache.lucene.analysis.ru.RussianLightStemmer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String phrase = "Я увлекаюсь футболом";

        // 1. Очистка фразы
        String[] ready = arrayReady(phrase);

        // 2. Получение нормальной формы
        List<String> result = getNoun(ready);

        // 3. Вывод
        if (!result.isEmpty()) {
            System.out.println("Результат: " + result.get(result.size() - 1));
        }
    }

    private static String[] arrayReady(String phrase) {
        // Оставляем только русские буквы
        String clean = phrase.replaceAll("[^а-яА-Я\\s]", "").toLowerCase();
        return clean.trim().split("\\s+");
    }

    private static List<String> getNoun(String[] array) {
        // Используем стеммер из Lucene (он у тебя точно есть в проекте)
        RussianLightStemmer stemmer = new RussianLightStemmer();
        List<String> processedWords = new ArrayList<>();

        for (String word : array) {
            // Пропускаем короткие слова (я, в, на)
            if (word.length() <= 3) continue;

            char[] chars = word.toCharArray();
            // Метод stem возвращает длину новой основы слова
            int length = stemmer.stem(chars, chars.length);

            String base = new String(chars, 0, length);
            processedWords.add(base);
        }
        return processedWords;
    }
}