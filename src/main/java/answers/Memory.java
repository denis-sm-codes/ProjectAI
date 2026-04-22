package answers;

import java.util.*;

public class Memory {

    private final Queue<SemanticPair> memory = new ArrayDeque<>(10);
    private final Random random = new Random();

    public void saveToMemory(String original, String mapped) {
        if (memory.size() >= 10) {
            memory.poll();
        }
        memory.add(new SemanticPair(original, mapped));
    }

    public SemanticPair pickRandomPair() {
        if (memory.isEmpty()) return null;

        SemanticPair[] items = memory.toArray(new SemanticPair[0]);
        return items[random.nextInt(items.length)];
    }

    public boolean memoryChek() {
        return !memory.isEmpty();
    }
}