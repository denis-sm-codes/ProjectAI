package answers;

public class SemanticPair {
    String originalWord; // То, что сказал пользователь (напр. "гонял")
    String mappedWord;   // То, что нашел ИИ по координатам (напр. "бежать")

    public SemanticPair(String original, String mapped) {
        this.originalWord = original;
        this.mappedWord = mapped;
    }

    public String getOriginalWord() { return originalWord; }
    public String getMappedWord() { return mappedWord; }
}