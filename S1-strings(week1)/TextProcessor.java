import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.split(" ");
        StringBuilder cleaned = new StringBuilder();
        for (String word : words) {
            cleaned.append(Character.toUpperCase(word.charAt(0)))
                   .append(word.substring(1).toLowerCase())
                   .append(" ");
        }
        return cleaned.toString().trim();
    }

    public static void analyzeText(String text) {
        int wordCount = text.split("\\s+").length;
        int sentenceCount = text.split("[.!?]").length;
        int charCount = text.replace(" ", "").length();
        String longestWord = "";
        for (String word : text.split("\\s+")) {
            if (word.length() > longestWord.length()) longestWord = word;
        }
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.replaceAll("\\s+", "").toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxFreq) {
                mostCommonChar = e.getKey();
                maxFreq = e.getValue();
            }
        }
        System.out.println("\n--- TEXT ANALYSIS ---");
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters (excluding spaces): " + charCount);
        System.out.println("Longest word: " + longestWord);
        System.out.println("Most common character: '" + mostCommonChar + "' (" + maxFreq + " times)");
    }

    public static String[] getWordsSorted(String text) {
        text = text.replaceAll("[^a-zA-Z ]", "");
        String[] words = text.split("\\s+");
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph of text: ");
        String inputText = scanner.nextLine();
        String cleanedText = cleanInput(inputText);
        System.out.println("\nCleaned Text: " + cleanedText);
        analyzeText(cleanedText);
        System.out.println("\n--- Words in Alphabetical Order ---");
        String[] sortedWords = getWordsSorted(cleanedText);
        for (String word : sortedWords) System.out.println(word);
        System.out.print("\nEnter a word to search: ");
        String searchWord = scanner.nextLine().trim();
        boolean found = Arrays.stream(sortedWords).anyMatch(w -> w.equalsIgnoreCase(searchWord));
        System.out.println(found ? "The word \"" + searchWord + "\" was found." : "The word \"" + searchWord + "\" was NOT found.");
        scanner.close();
    }
}

