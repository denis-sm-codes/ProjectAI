package answers;

import java.util.*;

public class Memory {
    // Используем LinkedHashMap: она помнит порядок добавления и позволяет быстро искать по ключу
    private final Map<String, String> memoryMap = new LinkedHashMap<>();
    private final Random random = new Random();
    private final int MAX_SIZE = 10;

    public void saveToMemory(String original, String mapped) {
        // Если такого ключа еще нет и память переполнена — удаляем самое старое
        if (!memoryMap.containsKey(original) && memoryMap.size() >= MAX_SIZE) {
            String firstKey = memoryMap.keySet().iterator().next();
            memoryMap.remove(firstKey);
        }

        // Сохраняем или обновляем. Если mapped == null, запишется пустая строка.
        memoryMap.put(original, (mapped != null) ? mapped : "");
    }

    public SemanticPair pickRandomPair() {
        if (memoryMap.isEmpty()) return null;

        // Выбираем случайный ключ
        List<String> keys = new ArrayList<>(memoryMap.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));

        return new SemanticPair(randomKey, memoryMap.get(randomKey));
    }

    // Метод для получения значения по ключу (чтобы проверить, пустое оно или нет)
    public String getValue(String key) {
        return memoryMap.get(key);
    }

    public boolean memoryChek() {
        return !memoryMap.isEmpty();
    }
}