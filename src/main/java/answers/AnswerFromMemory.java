package answers;

import java.util.List;
import java.util.Random;

public class AnswerFromMemory {
    private final Random random = new Random();
    private final Memory memory;

    public AnswerFromMemory(Memory memory) {
        this.memory = memory;
    }

    // ТЕПЕРЬ МЕТОД ПРИНИМАЕТ ПАРУ ИЗВНЕ
    public String generateFromHistory(SemanticPair pair) {
        if (pair == null) return null;

        String original = pair.getOriginalWord();
        String mapped = pair.getMappedWord();

        int choice = random.nextInt(6);

        if (choice == 0) {
            return "Мы говорили про " + correctionVowOne(original) + ", это как то связанно с " + dualCorrectionOne(original) + "?";
        } else if (choice == 1) {
            return "Ты упоминал " + correctionVowOne(original) + ". Это тема похожа на " + correctionVowOne(mapped) + "?";
        } else if (choice == 2) {
            return "Я помню ты упоминал " + correctionVowOne(original) + ", это похоже на " + correctionVowOne(mapped) + "?";
        } else if (choice == 3) {
            return "Что ещё ты можешь рассказать кроме " + dualCorrectionTwo(original) + " и " + dualCorrectionTwo(mapped) + "?";
        } else if (choice == 4) {
            return original.substring(0, 1).toUpperCase() + original.substring(1)
                    + " это интересно так же как и " + mapped + ", не правда ли?";
        } else {
            return "Я запомнил что мы говорили про " + correctionVowOne(original) + " и " + correctionVowOne(mapped);
        }
    }

    public String askAbout(String key) {
        List<String> templates = List.of(
                "Что для тебя означает " + key + "?",
                "А что такое " + key + " в твоем понимании?",
                "Мне интересно, а что ты подразумеваешь под словом " + key + "?",
                "Никак не пойму, " + key + " - это что?",
                "Что такое " + key + "?",
                "Расскажи мне про " + key,
                "Проясни мне про " + key
        );
        return templates.get(random.nextInt(templates.size()));
    }

    private String dualCorrectionOne(String word){
        if (word == null || word.isEmpty()){
            return word;
        }
        if (checkingCon(word)){
            return word.substring(0, word.length() - 1) + "ом";
        }
        if (checkingVow(word)){
            return word.substring(0, word.length() - 1) + "ой";
        }
        return word;
    }

    private String dualCorrectionTwo(String word){
        if (word == null || word.isEmpty()){
            return word;
        }
        if (checkingCon(word)){
            return word.substring(0, word.length() - 1) + "а";
        }
        if (checkingVow(word)){
            return word.substring(0, word.length() - 1) + "ы";
        }
        return word;
    }

    private String correctionConOne(String word){
        if (word == null || word.isEmpty()){
            return word;
        }
        if (checkingCon(word)){
            return word.substring(0, word.length() - 1) + "ом";
        }
        return word;
    }

    private String correctionConTwo(String word){
        if (word == null || word.isEmpty()){
            return word;
        }
        if (checkingCon(word)){
            return word.substring(0, word.length() - 1) + "а";
        }
        return word;
    }

    private String correctionVowOne(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        if (checkingVow(word)){
            return word.substring(0, word.length() - 1) + "у";
        }
        return word;
    }

    private String correctionVowTwo(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        if (checkingVow(word)){
            return word.substring(0, word.length() - 1) + "ы";
        }
        return word;
    }

    private boolean checkingCon(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        char last = word.charAt(word.length() - 1);
        return "бвгджзйклмнпрстфхцчшщ".indexOf(last) != -1;
    }

    private boolean checkingVow(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        char last = word.charAt(word.length() - 1);
        return "уеыаоэи".indexOf(last) != -1;
    }
}