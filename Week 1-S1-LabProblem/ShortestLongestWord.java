import java.util.Scanner;

public class ShortestLongestWord {

    static int getLength(String s) {
        int count = 0;
        try {
            while (true) {
                s.charAt(count);
                count++;
            }
        } catch (Exception e) {
        }
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
                if (!word.isEmpty()) {
                    words[index++] = word;
                    word = "";
                }
            } else {
                word += c;
            }
        }
        if (!word.isEmpty()) {
            words[index] = word;
        }
        return words;
    }

    static String[][] getWordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(getLength(words[i]));
        }
        return result;
    }

    static int[] findShortestLongest(String[][] wordLengthArray) {
        int shortestIndex = 0;
        int longestIndex = 0;

        for (int i = 1; i < wordLengthArray.length; i++) {
            int len = Integer.parseInt(wordLengthArray[i][1]);
            int shortestLen = Integer.parseInt(wordLengthArray[shortestIndex][1]);
            int longestLen = Integer.parseInt(wordLengthArray[longestIndex][1]);

            if (len < shortestLen) {
                shortestIndex = i;
            }
            if (len > longestLen) {
                longestIndex = i;
            }
        }
        return new int[]{shortestIndex, longestIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("No input given!");
            return;
        }

        String[] words = manualSplit(input);
        String[][] wordLengthArray = getWordsWithLengths(words);
        int[] result = findShortestLongest(wordLengthArray);

        System.out.println("\nWord\tLength");
        System.out.println("-----------------");
        for (String[] row : wordLengthArray) {
            System.out.println(row[0] + "\t" + row[1]);
        }

        System.out.println("\nShortest Word: " + wordLengthArray[result[0]][0] +
                " (Length: " + wordLengthArray[result[0]][1] + ")");
        System.out.println("Longest Word: " + wordLengthArray[result[1]][0] +
                " (Length: " + wordLengthArray[result[1]][1] + ")");
    }
}
