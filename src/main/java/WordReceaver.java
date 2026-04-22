import answers.*;

import java.util.List;
import java.util.Random;

public class WordReceaver {
    private final BestWordFind finder = new BestWordFind();
    private final Memory memory = new Memory();
    private final VectorService vectorService = new VectorService();

    private final AnswerForNoun nounHandler = new AnswerForNoun();
    private final AnswerForVerb verbHandler = new AnswerForVerb();
    private final AnswerForAdjective adjHandler = new AnswerForAdjective();
    private final Random random = new Random();

    private final List<String> list = List.of(
            "Расскажи что-нибудь еще", "Ну я тебя понял",
            "Интересно рассказываешь", "Хорошо, я запомнил",
            "Понял, а что ещё?", "Вот это да!"
    );

    public String getFinalResponse(String phrase) {
        try {
            LoggerShutter.startSilence();
            AnalysisResult mainAnalysis = finder.findBestWord(phrase);
            List<String> allWords = finder.findAllSignificantWords(phrase);
            LoggerShutter.stopSilence();

            // Сохраняем в память
            if (mainAnalysis != null && !allWords.isEmpty()) {
                String mappedValue = vectorService.findMeanWord(allWords);
                memory.saveToMemory(mainAnalysis.word, mappedValue);
            }

            // Если текущая фраза не понята — лезем в память
            if (mainAnalysis == null) {
                if (memory.memoryChek()) {
                    SemanticPair pair = memory.pickRandomPair();
                    return "Мы тут говорили про '" + pair.getOriginalWord() + "'. " +
                            "Это ведь связано с темой '" + pair.getMappedWord() + "', да?";
                }
                return list.get(random.nextInt(list.size()));
            }

            // Обычные ответы
            return switch (mainAnalysis.pos) {
                case "NN" -> nounHandler.generate(mainAnalysis.word);
                case "VB" -> verbHandler.generate(mainAnalysis.word);
                case "ADJ" -> adjHandler.generate(mainAnalysis.word);
                default -> "Интересное слово: " + mainAnalysis.word;
            };

        } catch (Exception e) {
            LoggerShutter.stopSilence();
            return "Ой, я немного запутался в мыслях...";
        }
    }
}