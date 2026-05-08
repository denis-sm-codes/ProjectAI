import answers.Memory;
import answers.AnswerFromMemory;
import answers.AnalysisResult;
import answers.SemanticPair;
import answers.AnswerForNoun;
import answers.AnswerForVerb;
import answers.AnswerForAdjective;
import answers.DefaultResponses;

import java.util.Random;

public class FinalResponse {
    private final BestWordFind finder = new BestWordFind();
    private final Memory memory = new Memory();
    private final AnswerFromMemory memoryHandler = new AnswerFromMemory(memory);

    private final AnswerForNoun nounHandler = new AnswerForNoun();
    private final AnswerForVerb verbHandler = new AnswerForVerb();
    private final AnswerForAdjective adjHandler = new AnswerForAdjective();
    private final DefaultResponses defaultResponses = new DefaultResponses();

    private final Random random = new Random();

    // Поле состояния для ожидания ответа
    private String awaitingDefinitionFor = null;

    public String getFinalResponse(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            return defaultResponses.getEmptyResponse();
        }

        try {
            // 1. ПРОВЕРКА: Ждем ли мы определение?
            if (awaitingDefinitionFor != null) {
                AnalysisResult definition = finder.findBestWord(phrase);
                if (definition != null) {
                    String key = awaitingDefinitionFor;
                    String val = definition.word;

                    memory.saveToMemory(key, val); // Сохраняем связь
                    awaitingDefinitionFor = null;  // Сбрасываем режим ожидания

                    return "Теперь я буду знать, что " + key + " — это " + val + "!";
                }
            }

            // 2. АНАЛИЗ ТЕКУЩЕЙ ФРАЗЫ
            AnalysisResult mainAnalysis = finder.findBestWord(phrase);
            if (mainAnalysis != null) {
                // Если слова еще нет в памяти — создаем ключ с пустым значением
                // Мы используем метод getValue, который вернет null, если ключа нет
                if (memory.getValue(mainAnalysis.word) == null) {
                    memory.saveToMemory(mainAnalysis.word, "");
                }
            }

            // 3. ЖРЕБИЙ (50% шанс пойти в память)
            if (random.nextInt(2) == 1 && memory.memoryChek()) {
                SemanticPair pair = memory.pickRandomPair();

                // Если значения нет (пустота) — задаем уточняющий вопрос
                if (pair.getMappedWord() == null || pair.getMappedWord().isEmpty()) {
                    awaitingDefinitionFor = pair.getOriginalWord();
                    return memoryHandler.askAbout(awaitingDefinitionFor);
                } else {
                    // Если значение есть — используем старую добрую историю
                    return memoryHandler.generateFromHistory(pair);
                }
            }

            // 4. ОБЫЧНАЯ ЛОГИКА (Если память не сработала)
            if (mainAnalysis == null) return defaultResponses.getRandomResponse();

            return switch (mainAnalysis.pos) {
                case "NN" -> nounHandler.generate(mainAnalysis.word);
                case "VB" -> verbHandler.generate(mainAnalysis.word);
                case "ADJ" -> adjHandler.generate(mainAnalysis.word);
                default -> defaultResponses.getRandomResponse();
            };

        } catch (Exception e) {
            return defaultResponses.getRandomResponse();
        }
    }
}