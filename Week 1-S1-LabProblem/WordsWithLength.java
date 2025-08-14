import java.util.Scanner;

public class WordsWithLength {

    
    static int getLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (Exception e) { }
        return count;
    }

    
    static String[] manualSplit(String s) {
        int len = getLength(s);
        int spaces = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') spaces++;
        }

        String[] words = new String[spaces + 1];
        String word = "";
        int index = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                words[index++] = word;
                word = "";
            } else {
                word += c;
            }
        }
        words[index] = word; 
        return words;
    }

    
    static String[][] getWordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i]; // word
            result[i][1] = String.valueOf(getLength(words[i])); 
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        
        String[] words = manualSplit(input);

        
        String[][] wordLengthArray = getWordsWithLengths(words);

        
        System.out.println("\nWord\tLength");
        System.out.println("-----------------");
        for (int i = 0; i < wordLengthArray.length; i++) {
            String word = wordLengthArray[i][0];
            int length = Integer.parseInt(wordLengthArray[i][1]); 
            System.out.println(word + "\t" + length);
        }
    }
}


