package answers;

import java.util.List;

public class VectorService {
    // Здесь позже будет магия Word2Vec (среднее арифметическое векторов)
    public String findMeanWord(List<String> words) {
        if (words == null || words.isEmpty()) return "пустота";
        // Пока возвращаем последнее значимое слово как "смысл" всей фразы
        return words.get(words.size() - 1);
    }
}