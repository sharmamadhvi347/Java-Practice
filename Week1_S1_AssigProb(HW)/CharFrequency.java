import java.util.Scanner;

public class CharFrequency {

    public static String[][] findFrequency(String text) {
        int[] freq = new int[256];
        int length = 0;
        try {
            while (true) {
                text.charAt(length);
                length++;
            }
        } catch (StringIndexOutOfBoundsException e) {}

        for (int i = 0; i < length; i++) {
            freq[text.charAt(i)]++;
        }

        String[][] result = new String[length][2];
        int index = 0;
        for (int i = 0; i < length; i++) {
            char ch = text.charAt(i);
            if (freq[ch] != 0) {
                result[index][0] = String.valueOf(ch);
                result[index][1] = String.valueOf(freq[ch]);
                freq[ch] = 0;
                index++;
            }
        }

        String[][] finalResult = new String[index][2];
        for (int i = 0; i < index; i++) {
            finalResult[i][0] = result[i][0];
            finalResult[i][1] = result[i][1];
        }

        return finalResult;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[][] freqResult = findFrequency(text);

        System.out.println("Character\tFrequency");
        for (String[] row : freqResult) {
            System.out.println(row[0] + "\t\t" + row[1]);
        }
        sc.close();
    }
}


