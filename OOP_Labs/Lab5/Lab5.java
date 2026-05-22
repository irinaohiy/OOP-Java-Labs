import java.util.ArrayList;
import java.util.List;

class Letter {
    private final char value;
    public Letter(char value) { this.value = value; }
    public char getValue() { return value; }
}

class Word {
    private final Letter[] letters;
    public Word(String wordStr) {
        this.letters = new Letter[wordStr.length()];
        for (int i = 0; i < wordStr.length(); i++) {
            this.letters[i] = new Letter(wordStr.charAt(i));
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter l : letters) sb.append(l.getValue());
        return sb.toString();
    }
}

class Sentence {
    private final Object[] elements; // Зберігає і об'єкти Word, і розділові знаки String

    public Sentence(String sentenceStr) {
        String[] tokens = sentenceStr.split("(?=[\\s,;.!?-])|(?<=[\\s,;.!?-])");
        this.elements = new Object[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].matches("[\\s,;.!?-]+")) this.elements[i] = tokens[i];
            else this.elements[i] = new Word(tokens[i]);
        }
    }
    public List<Word> getWords() {
        List<Word> wordsList = new ArrayList<>();
        for (Object el : elements) {
            if (el instanceof Word) wordsList.add((Word) el);
        }
        return wordsList;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object el : elements) sb.append(el.toString());
        return sb.toString();
    }
}

class Text {
    private final Sentence[] sentences;
    public Text(String rawInput) {
        // Вимоги нормалізації тексту: заміна пробілів та табуляцій на один пробіл
        String normalizedText = rawInput.replaceAll("[\\t ]+", " ");
        String[] sentenceStrings = normalizedText.split("(?<=[.!?])\\s*");
        this.sentences = new Sentence[sentenceStrings.length];
        for (int i = 0; i < sentenceStrings.length; i++) {
            this.sentences[i] = new Sentence(sentenceStrings[i]);
        }
    }
    public List<Word> getAllWords() {
        List<Word> allWords = new ArrayList<>();
        for (Sentence s : sentences) allWords.addAll(s.getWords());
        return allWords;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence s : sentences) sb.append(s.toString()).append(" ");
        return sb.toString().trim();
    }
}

public class Lab5 {
    public static void main(String[] args) {
        String input = "Apple\t\tbanana, orange!   An elephant   and  islands  are here.";
        Text text = new Text(input);
        
        System.out.println("Normalized Text Output:");
        System.out.println(text);

        // Виконання дії з Лабораторної №3 за допомогою нових класів
        List<Word> filteredWords = new ArrayList<>();
        String vowels = "aeiouyAEIOUYаеєиіїоуюяАЕЄИІЇОУЮЯ";

        for (Word word : text.getAllWords()) {
            String wStr = word.toString();
            if (!wStr.isEmpty() && vowels.indexOf(wStr.charAt(0)) != -1) {
                filteredWords.add(word);
            }
        }

        filteredWords.sort((w1, w2) -> {
            String s1 = w1.toString(); String s2 = w2.toString();
            char c1 = (s1.length() > 1) ? s1.charAt(1) : s1.charAt(0);
            char c2 = (s2.length() > 1) ? s2.charAt(1) : s2.charAt(0);
            return Character.compare(Character.toLowerCase(c1), Character.toLowerCase(c2));
        });

        System.out.println("\nAction from Lab 3 executed on Object Model:");
        for (Word w : filteredWords) System.out.println("- " + w);
    }
}