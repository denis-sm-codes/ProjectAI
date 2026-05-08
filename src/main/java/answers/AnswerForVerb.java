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
    int[] algoritmSeven = {16, 7};
    int[] algoritmEight = {18, 19};


    public String generate(String verb) {

        String finalVerb = resultForm(verb);
        int resultFlag = methodFlag;
        if (resultFlag == 1){
            int index = random.nextInt(algoritmInfinitive.length);

        }
        if (resultFlag == 2){
            int index = random.nextInt(algoritmFormOne.length);

        }
        if (resultFlag == 3){
            int index = random.nextInt(algoritmFormTwo.length);

        }





        int queIndex = random.nextInt(firstPart.size());
        String question = firstPart.get(queIndex);

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
            default -> throw new IllegalStateException("Внезапный сбой логики!");
        };

        int secondIndex = selectedAlgoritm[random.nextInt(selectedAlgoritm.length)];
        String link = secondPart.get(secondIndex);


        return question + " " + link + "  "+ "?";
    }

    private String resultForm(String verb) {
        if (verb.endsWith("ать") || verb.endsWith("ять")){
            if (random.nextInt(2) == 0){
                methodFlag = 1;
                return verb;
            } else {
                methodFlag = 2;
                return toFormOne(verb);
            }
        }
        if (verb.endsWith("ить")){
            if (random.nextInt(2) == 0){
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
        if (word == null || word.isEmpty()) { return word; }

        if (word.endsWith("ать")){
            return word.substring(0, word.length() - 2) + "ет";
        }
        if (word.endsWith("ять")){
            return word.substring(0, word.length() - 2) + "ет";
        }
        return word;
    }

    private String toFormTwo(String word) {
        if (word == null || word.isEmpty()) { return word; }

        if (word.endsWith("ить")){
            return word.substring(0, word.length() - 1);
        }
        return word;
    }










//    public String generate(String verb) {
//        int choice = random.nextInt(4); // Выбираем из 4 вариантов
//
//        if (choice == 0) {
//            return "О, ты любишь " + verb + "? Расскажи, почему это тебе нравится?";
//        } else if (choice == 1) {
//            return "Мне кажется, " + verb + " — это отличное времяпровождение!";
//        } else if (choice == 2) {
//            return "А как часто ты стараешься " + verb + "?";
//        } else {
//            return "Интересно... Я бы тоже хотел попробовать " + verb + ".";
//        }
//    }
}