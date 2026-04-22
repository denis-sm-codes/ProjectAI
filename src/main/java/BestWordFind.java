import answers.AnalysisResult;
import org.languagetool.tagging.ru.RussianTagger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestWordFind {

    public AnalysisResult findBestWord(String phrase) throws Exception {
        String[] words = cleanPhrase(phrase);
        RussianTagger tagger = new RussianTagger();
        String foundVerb = null;
        String foundAdjective = null;

        for (String word : words) {
            if (word.length() <= 2) continue;
            List<?> readings = getReadings(tagger, word);

            for (Object tagged : readings) {
                String lemma = (String) tagged.getClass().getMethod("getLemma").invoke(tagged);
                Object posObj = tagged.getClass().getMethod("getPOSTag").invoke(tagged);
                String pos = (posObj == null) ? "" : posObj.toString();

                if (pos.startsWith("NN")) return new AnalysisResult(lemma, "NN");
                if (pos.startsWith("VB") && foundVerb == null) foundVerb = lemma;
                if (pos.startsWith("ADJ") && foundAdjective == null) foundAdjective = lemma;
            }
        }
        if (foundVerb != null) return new AnalysisResult(foundVerb, "VB");
        if (foundAdjective != null) return new AnalysisResult(foundAdjective, "ADJ");
        return null;
    }

    public List<String> findAllSignificantWords(String phrase) throws Exception {
        List<String> allWords = new ArrayList<>();
        String[] words = cleanPhrase(phrase);
        RussianTagger tagger = new RussianTagger();

        for (String word : words) {
            if (word.length() <= 2) continue;
            List<?> readings = getReadings(tagger, word);

            for (Object tagged : readings) {
                String lemma = (String) tagged.getClass().getMethod("getLemma").invoke(tagged);
                Object posObj = tagged.getClass().getMethod("getPOSTag").invoke(tagged);
                String pos = (posObj == null) ? "" : posObj.toString();

                if (pos.startsWith("NN") || pos.startsWith("VB") || pos.startsWith("ADJ")) {
                    allWords.add(lemma);
                    break;
                }
            }
        }
        return allWords;
    }

    private String[] cleanPhrase(String phrase) {
        return phrase.replaceAll("[^а-яА-Я\\s]", "").toLowerCase().trim().split("\\s+");
    }

    private List<?> getReadings(RussianTagger tagger, String word) throws Exception {
        List<?> tokenReadingsList = tagger.tag(Collections.singletonList(word));
        if (tokenReadingsList == null || tokenReadingsList.isEmpty()) return Collections.emptyList();
        Object firstToken = tokenReadingsList.get(0);
        return (List<?>) firstToken.getClass().getMethod("getReadings").invoke(firstToken);
    }
}