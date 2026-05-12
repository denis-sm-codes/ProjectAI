package answers;

import java.util.List;
import java.util.Random;

public class AnswerForVerb {
    private final Random random = new Random();
    private int methodFlag = 0;

    private final List<String> firstPart = List.of("Кто", "Что", "Какой", "Как",
            "Где", "Когда", "Почему", "Зачем", "Сколько");

    private final List<String> secondPart = List.of(
            "обычно любит", "всегда лучше", "чаще выбирает", "всегда",   // 0-3
            "нужно что бы начать", "всегда лучше", "значит",                       // 4-6
            "навык нужен что-бы", "способ лучше",                                  // 7-8
            "лучше", "надо правильно",                                             // 9-10
            "можно", "чаще всего кто-то",                                          // 11-12
            "лучше начинать", "надо",                                              // 13-14
            "людям нравится",                                                      // 15
            "надо", "нужно ",                                                      // 16-17
            "люди хотят", "нужно");                                                // 18-19

    // работать, разбавлять, строить

    // ать, ять, ить(изначальная форма, метод окончания не нужен) - 0,2,4,6,7,9,10,11,13,14,15,16,17,18,29
    // ет(от ать, ять) - 1,3,5,8,12
    // ит(от ить) - 1,3,5,8,12

    // 1. Сначала глагол проверяется на окончание (ать, ять, ить).
    // 2. Если главгол -ать или -ять то далее разыгрывается рандом из 2-х варинатов(algoritmInfinitive, algoritmFormOne)
    // 3. Если главгол -ять, то далее разыгрывается рандом из 2-х варинатов(algoritmInfinitive, algoritmFormTwo)
    // 4. Далее он отправляется в метод что бы поменять окончание согласно своему жребию
    // 5. Далее берётся один из 3-х алгоритмов(algoritmInfinitive, algoritmFormOne, algoritmFormTwo)
    // 6. В зависимости того, что ему выпало из пункта 4, и берёт случайным образом любой элемент из этоого массива
    // 7. Далее этот элемент используется как индекс поиска элемента из коллекции secondPart
    // 8. Далее этот элемент используется как индекс поиска элемента из коллекции firstPart
    // Но нам надо найти этот индекс из массивов algoritmZero.... algoritmEight, допутим мы нашли индекс в массиве algoritmOne
    // algoritmOne соответствует элементу из массива firstPart под индексом 1. Название algoritmOne говорит об этом там в конце One
    // И в итоге у нас получает созданный вопрос. Было работать, а стало Что всегда лучше работает


    int[] algoritmInfinitive = {0, 2, 4, 6, 7, 9, 10, 11, 13, 14, 15, 16, 17, 18, 29};
    int[] algoritmFormOne = {1, 3, 5, 8, 12};
    int[] algoritmFormTwo = {1, 3, 5, 8, 12};


    int[] algoritmZero = {0, 1, 2, 3};
    int[] algoritmOne = {4, 5, 6};
    int[] algoritmTwo = {7, 8};
    int[] algoritmThree = {9, 10};
    int[] algoritmFour = {11, 12};
    int[] algoritmFive = {13, 14};
    int[] algoritmSix = {15};
    int[] algoritmSeven = {16, 17};
    int[] algoritmEight = {18, 19};


    public String generate (String verb) {
            String finalVerb = resultForm(verb); // Сначала определяем форму глагола и методFlag
            int index = 0;

            // 1. Выбираем случайный ИНДЕКС из доступных для данной формы глагола
            if (methodFlag == 1) index = algoritmInfinitive[random.nextInt(algoritmInfinitive.length)];
            else if (methodFlag == 2) index = algoritmFormOne[random.nextInt(algoritmFormOne.length)];
            else if (methodFlag == 3) index = algoritmFormTwo[random.nextInt(algoritmFormTwo.length)];

            int finalIndex = 0;
            String finalSecond = "";

            // 2. Ищем, к какой группе относится этот индекс, и берем случайное слово из ЭТОЙ группы
            if (contains(algoritmZero, index)) {
                finalIndex = 0;
                finalSecond = pickRandom(algoritmZero);
            } else if (contains(algoritmOne, index)) {
                finalIndex = 1;
                finalSecond = pickRandom(algoritmOne);
            } else if (contains(algoritmTwo, index)) {
                finalIndex = 2;
                finalSecond = pickRandom(algoritmTwo);
            } else if (contains(algoritmThree, index)) {
                finalIndex = 3;
                finalSecond = pickRandom(algoritmThree);
            } else if (contains(algoritmFour, index)) {
                finalIndex = 4;
                finalSecond = pickRandom(algoritmFour);
            } else if (contains(algoritmFive, index)) {
                finalIndex = 5;
                finalSecond = pickRandom(algoritmFive);
            } else if (contains(algoritmSix, index)) {
                finalIndex = 6;
                finalSecond = pickRandom(algoritmSix);
            } else if (contains(algoritmSeven, index)) {
                finalIndex = 7;
                finalSecond = pickRandom(algoritmSeven);
            } else if (contains(algoritmEight, index)) {
                finalIndex = 8;
                finalSecond = pickRandom(algoritmEight);
            }

            // 3. Теперь берем первое слово, когда finalIndex УЖЕ определен
            String firstWord = firstPart.get(finalIndex);

            return firstWord + " " + finalSecond + " " + finalVerb + "?";
        }


    // Помощник: берет случайную строку из secondPart по индексам из массива
    private String pickRandom(int[] array) {
        int randomIndex = array[random.nextInt(array.length)];
        return secondPart.get(randomIndex);
    }

    // Помощник: проверяет наличие числа в массиве
    private boolean contains(int[] array, int key) {
        for (int i : array) {
            if (i == key) return true;
        }
        return false;
    }


    private String resultForm(String verb) {
        if (verb.endsWith("ать") || verb.endsWith("ять")) {
            if (random.nextInt(2) == 0) {
                methodFlag = 1;
                return verb;
            } else {
                methodFlag = 2;
                return toFormOne(verb);
            }
        }
        if (verb.endsWith("ить")) {
            if (random.nextInt(2) == 0) {
                methodFlag = 1;
                return verb;
            } else {
                methodFlag = 3;
                return toFormTwo(verb);
            }
        }
        return verb;
    }

    private String toFormOne(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        if (word.endsWith("ать")) {
            return word.substring(0, word.length() - 2) + "ет";
        }
        if (word.endsWith("ять")) {
            return word.substring(0, word.length() - 2) + "ет";
        }
        return word;
    }

    private String toFormTwo(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        if (word.endsWith("ить")) {
            return word.substring(0, word.length() - 1);
        }
        return word;
    }
}