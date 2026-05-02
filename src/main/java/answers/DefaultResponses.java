package answers;

import java.util.List;
import java.util.Random;

public class DefaultResponses {
    private final Random random = new Random();

    private final List<String> list = List.of(
            "Расскажи что-нибудь еще",
            "Ну я тебя понял",
            "Интересно рассказываешь",
            "Хорошо, я запомнил",
            "Понял, а что ещё?",
            "Вот это да!"
    );

    private final List<String> forEmpty = List.of(
            "Что?",
            "Ты что-то говорил?",
            "Не понял",
            "Ты ничего не написал",
            "Ты отправил пустую строку",
            "Ты хотел что-то сказать?",
            "Ты нажал enter, ничего не написав",
            "А?"
    );

    // Когда пользователь прислал абракадабру или бот не нашел ключевых слов
    public String getRandomResponse() {
        return list.get(random.nextInt(list.size()));
    }

    // Когда в строке совсем ничего нет (пробелы или пустой enter)
    public String getEmptyResponse() {
        return forEmpty.get(random.nextInt(forEmpty.size()));
    }
}