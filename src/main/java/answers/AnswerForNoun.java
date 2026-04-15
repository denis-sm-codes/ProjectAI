package answers;

import java.util.Random;

public class AnswerForNoun {
    private final Random random = new Random();

    public String generate(String noun) {
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

    // Метод для Творительного падежа (Кем? Чем?)
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