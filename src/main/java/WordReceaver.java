import answers.AnswerForAdjective;
import answers.AnswerForNoun;
import answers.AnswerForVerb;

public class WordReceaver {
    private final BestWordFind finder = new BestWordFind();
    private final AnswerForNoun nounHandler = new AnswerForNoun();
    private final AnswerForVerb verbHandler = new AnswerForVerb();
    private final AnswerForAdjective adjHandler = new AnswerForAdjective();

    public String getFinalResponse(String phrase) {
        try {
            LoggerShutter.startSilence();
            AnalysisResult analysis = finder.findBestWord(phrase);
            LoggerShutter.stopSilence();

            if (analysis == null) {
                return "Хм, интересно. Расскажи что-нибудь еще?";
            }

            // ВАЖНО: Здесь мы вызываем методы классов, а не пишем текст руками!
            if (analysis.pos.equals("NN")) {
                return nounHandler.generate(analysis.word);
            }

            if (analysis.pos.equals("VB")) {
                // Раньше тут была строка "Ты любишь...", а теперь ВЫЗОВ метода
                return verbHandler.generate(analysis.word);
            }

            if (analysis.pos.equals("ADJ")) {
                // Раньше тут была строка "Интересная мысль...", а теперь ВЫЗОВ метода
                return adjHandler.generate(analysis.word);
            }

            return "Интересное слово: " + analysis.word;

        } catch (Exception e) {
            LoggerShutter.stopSilence();
            return "Ой, я немного запутался в мыслях...";
        }
    }
}