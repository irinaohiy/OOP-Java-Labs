import java.util.ArrayList;
import java.util.List;

public class Lab3 {
    public static void main(String[] args) {
        String text = "Apple, orange, banana, ice and elephant. І екрани працюють!";
        
        // Токенізація: очищення від знаків пунктуації за допомогою RegEx
        String[] words = text.split("[\\s,;.!?-]+");
        List<String> vowelWords = new ArrayList<>();
        String vowels = "aeiouyAEIOUYаеєиіїоуюяАЕЄИІЇОУЮЯ";

        // Фільтрація слів, що починаються з голосних літер
        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = word.charAt(0);
                if (vowels.indexOf(firstChar) != -1) {
                    vowelWords.add(word);
                }
            }
        }

        // Кастомне сортування за 2-ю літерою (індекс 1)
        vowelWords.sort((w1, w2) -> {
            char c1 = (w1.length() > 1) ? w1.charAt(1) : w1.charAt(0);
            char c2 = (w2.length() > 1) ? w2.charAt(1) : w2.charAt(0);
            return Character.compare(Character.toLowerCase(c1), Character.toLowerCase(c2));
        });

        System.out.println("Filtered and sorted words:");
        for (String w : vowelWords) System.out.println("- " + w);
    }
}