package answers;

import java.util.List;
import java.util.Random;

public class AnswerForNoun {
    private final Random random = new Random();

    private final List<String> firstPart = List.of("Кто", "Что", "Какой", "Чей",
            "Как", "Где", "Куда", "Когда", "Почему", "Зачем", "Сколько");

    private final List<String> secondPart= List.of("обычно увлекается", "любит занимается", "чаще выбирает", "отвечает за",
            "ты подразумеваешь под", "по твоему означает за", "нужно для",
            "ты думаешь бывает", "ты знаешь вид", "ты предпочитаешь",
            "по твоему это", "ты видел",
            "ты узнал о", "ты дошёл до", "тебе в целом",
            "ты впервые увидел", "чаще всего есть",
            "надо отправитьсяя что-бы увидеть",
            "ты ", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");

    int[] algoritmZero = {0, 1, 2, 3};
    int[] algoritmOne = {4, 5, 6};
    int[] algoritmTwo = {7, 8, 9};
    int[] algoritmThree = {10, 11};
    int[] algoritmFour = {12, 13, 14};
    int[] algoritmFive = {7, 11, 15, 16};
    int[] algoritmSix = {17};
    int[] algoritmSeven = {9, 11, 12, 15, 16};


    public String generate(String noun) {
        int form = random.nextInt(2);
        int queStart = random.nextInt(11);
        int choice = random.nextInt(3);

        if (choice == 0) {
            return "И как давно ты увлекаешься " + toTvorit(noun) + "?";
        } else if (choice == 1) {
            return "Круто, " + noun + " — это действительно интересно!";
        } else {
            return "Как много ты знаешь о " + toPredlozh(noun) + "?";
        }
    }

    // Метод для Предложного падежа (О ком? О чем?)
    private String toPredlozh(String word) {
        // Если слово на согласную (слон, компьютер) -> добавляем 'е'
        if (isConsonant(word.charAt(word.length() - 1))) {
            return word + "е";
        }
        // Если слово на 'а' или 'о' (машина, окно) -> меняем на 'е'
        if (word.endsWith("а") || word.endsWith("о")) {
            return word.substring(0, word.length() - 1) + "е";
        }
        return word; // Если правило не подошло, возвращаем как есть
    }

    //(Кем? Чем?) Футболом, Аэробикой, Плаваньем
    private String toTvorit(String word) {
        if (isConsonant(word.charAt(word.length() - 1))) {
            return word + "ом"; // слон -> слоном
        }
        if (word.endsWith("а")) {
            return word.substring(0, word.length() - 1) + "ой"; // машина -> машиной
        }
        return word;
    }

    // Вспомогательная проверка: является ли буква согласной
    private boolean isConsonant(char c) {
        return "бвгджзйклмнпрстфхцчшщ".indexOf(Character.toLowerCase(c)) != -1;
    }
}