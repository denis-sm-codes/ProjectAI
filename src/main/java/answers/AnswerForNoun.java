package answers;

import java.util.List;
import java.util.Random;

public class AnswerForNoun {
    private final Random random = new Random();

    private final List<String> firstPart = List.of("?Кто", "?Что", "?Какой", "?Чей",
            "?Как", "?Где", "?Куда", "?Когда", "?Почему", "?Зачем", "?Сколько");

    private final List<String> secondPart = List.of(
            "обычно увлекается", "любит заниматься", "чаще выбирает", "отвечает за", // 0-3
            "ты подразумеваешь под", "по твоему означает за", "нужно для",          // 4-6
            "ты думаешь бывает", "ты знаешь вид", "ты предпочитаешь",              // 7-9
            "по твоему это", "ты видел",                                           // 10-11
            "ты узнал о", "ты дошёл до", "тебе в целом",                           // 12-14
            "ты впервые увидел", "чаще всего есть",                                // 15-16
            "надо отправиться, чтобы увидеть",                                     // 17
            "ты интересуешься", "тебе интересна тема",                             // 18-19
            "вообще думать о",                                                     // 20
            "времени уходит на", "нужно заплатить за");                            // 21-22

    int[] algoritmZero = {0, 1, 2, 3};
    int[] algoritmOne = {4, 5, 6};
    int[] algoritmTwo = {7, 8, 9};
    int[] algoritmThree = {10, 11};
    int[] algoritmFour = {12, 13, 14};
    int[] algoritmFive = {7, 11, 15, 16};
    int[] algoritmSix = {17};
    int[] algoritmSeven = {9, 11, 12, 15, 16};
    int[] algoritmEight = {9, 18, 19};
    int[] algoritmNine = {14, 18, 20};
    int[] algoritmTen = {18, 21, 22};

    public String generate(String noun) {
        // 1. Выбираем случайный вопрос
        int queIndex = random.nextInt(firstPart.size());
        String question = firstPart.get(queIndex);

        // 2. Выбираем массив индексов связок (алгоритм) по индексу вопроса
        int[] selectedAlgoritm = switch (queIndex) {
            case 0 -> algoritmZero;
            case 1 -> algoritmOne;
            case 2 -> algoritmTwo;
            case 3 -> algoritmThree;
            case 4 -> algoritmFour;
            case 5 -> algoritmFive;
            case 6 -> algoritmSix;
            case 7 -> algoritmSeven;
            case 8 -> algoritmEight;
            case 9 -> algoritmNine;
            case 10 -> algoritmTen;
            default -> throw new IllegalStateException("Внезапный сбой логики!");
        };

        // 3. Выбираем индекс связки из алгоритма
        int secondIndex = selectedAlgoritm[random.nextInt(selectedAlgoritm.length)];
        String link = secondPart.get(secondIndex);

        // 4. Применяем нужный падеж, который диктует связка (secondIndex)
        String processedNoun = applyCorrectCase(secondIndex, noun);

        return question + " " + link + " " + processedNoun + "?";
    }

    private String applyCorrectCase(int index, String noun) {
        // Творительный: увлекается(0), занимается(1), под(4), за(5), интересуешься(18), времени на(21)
        if (List.of(0, 1, 4, 5, 18, 21).contains(index)) return toTvorit(noun);

        // Предложный: о(12), тема(19), думать о(20)
        if (List.of(12, 19, 20).contains(index)) return toPredlozh(noun);

        // Родительный: вид(8), дошёл до(13)
        if (List.of(8, 13).contains(index)) return toRodit(noun);

        // Винительный: выбирает(2), за(3 - отвечает за что), видел(11), увидел(15), заплатить за(22)
        if (List.of(2, 3, 11, 15, 22).contains(index)) return toVinit(noun);

        // Именительный (как есть): для(6), бывает(7), предпочитаешь(9), это(10), есть(16), увидеть(17), в целом(14)
        return noun;
    }

    // (О ком? О чем?) О Футболе, Об Аэробике, О Плаванье. Применяется для связок: 12, 19, 20
    private String toPredlozh(String word) {
        if (word == null || word.isEmpty()) return word;
        char lastChar = word.charAt(word.length() - 1);
        if (isConsonant(lastChar)) return word + "е";
        if (lastChar == 'а' || lastChar == 'о') return word.substring(0, word.length() - 1) + "е";
        return word;
    }

    // (Кем? Чем?) Футболом, Аэробикой, Плаваньем. Применяется для связок: 0, 1, 4, 5, 18, 21
    private String toTvorit(String word) {
        if (word == null || word.isEmpty()) return word;
        char lastChar = word.charAt(word.length() - 1);
        if (lastChar == 'о' || lastChar == 'е') return word + "м";
        if (lastChar == 'а') return word.substring(0, word.length() - 1) + "ой";
        if (lastChar == 'я') return word.substring(0, word.length() - 1) + "ей";
        if (isConsonant(lastChar)) return word + "ом";
        return word;
    }

    // (Кого? Что?) Футбол, Аэробику, Плаванье. Применяется для связок: 2, 3, 11, 15, 22
    private String toVinit(String word) {
        if (word == null || word.isEmpty()) return word;
        char lastChar = word.charAt(word.length() - 1);
        if (lastChar == 'а') return word.substring(0, word.length() - 1) + "у";
        if (lastChar == 'я') return word.substring(0, word.length() - 1) + "ю";
        return word;
    }

    // (Кого? Чего?) Футбола, Аэробики, Плаванья. Применяется для связок: 8, 13
    private String toRodit(String word) {
        if (word == null || word.isEmpty()) return word;
        char lastChar = word.charAt(word.length() - 1);
        if (isConsonant(lastChar)) return word + "а";
        if (lastChar == 'а') return word.substring(0, word.length() - 1) + "ы";
        if (lastChar == 'я') return word.substring(0, word.length() - 1) + "и";
        if (lastChar == 'о') return word.substring(0, word.length() - 1) + "а";
        if (lastChar == 'е') return word.substring(0, word.length() - 1) + "я";
        return word;
    }

    private boolean isConsonant(char c) {
        return "бвгджзйклмнпрстфхцчшщ".indexOf(Character.toLowerCase(c)) != -1;
    }
}