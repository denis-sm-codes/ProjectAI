import org.languagetool.tagging.ru.RussianTagger;
import java.util.Collections;
import java.util.List;

public class BestWordFind {

    public AnalysisResult findBestWord(String phrase) throws Exception {
        // Оставляем только русские буквы и пробелы
        String clean = phrase.replaceAll("[^а-яА-Я\\s]", "").toLowerCase();
        String[] words = clean.trim().split("\\s+");

        RussianTagger tagger = new RussianTagger();
        String foundVerb = null;
        String foundAdjective = null;

        for (String word : words) {
            if (word.length() <= 2) continue;

            // Получаем теги для слова
            List<?> tokenReadingsList = tagger.tag(Collections.singletonList(word));

            if (tokenReadingsList != null && !tokenReadingsList.isEmpty()) {
                Object firstToken = tokenReadingsList.get(0);

                // Используем рефлексию для работы с внутренними классами библиотеки
                java.lang.reflect.Method getReadings = firstToken.getClass().getMethod("getReadings");
                List<?> readings = (List<?>) getReadings.invoke(firstToken);

                for (Object tagged : readings) {
                    java.lang.reflect.Method getLemma = tagged.getClass().getMethod("getLemma");
                    java.lang.reflect.Method getPos = tagged.getClass().getMethod("getPOSTag");

                    String lemma = (String) getLemma.invoke(tagged);
                    String pos = (String) getPos.invoke(tagged);

                    if (pos == null || lemma == null) continue;

                    // ПРИОРИТЕТ 1: Существительное (Noun)
                    if (pos.startsWith("NN")) {
                        return new AnalysisResult(lemma, "NN");
                    }

                    // ПРИОРИТЕТ 2: Глагол (Verb)
                    if (pos.startsWith("VB") && foundVerb == null) {
                        foundVerb = lemma;
                    }

                    // ПРИОРИТЕТ 3: Прилагательное (Adjective)
                    if (pos.startsWith("ADJ") && foundAdjective == null) {
                        foundAdjective = lemma;
                    }
                }
            }
        }

        // Если существительное не нашли, возвращаем то, что удалось собрать
        if (foundVerb != null) return new AnalysisResult(foundVerb, "VB");
        if (foundAdjective != null) return new AnalysisResult(foundAdjective, "ADJ");

        return null;
    }
}