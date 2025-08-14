import java.util.Scanner;

public class SplitCompare {

   
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

   
    static boolean compare(String[] a, String[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] manual = manualSplit(input);
        String[] builtin = input.split(" ");

        System.out.println("\nManual Split:");
        for (String w : manual) System.out.println(w);

        System.out.println("\nBuilt-in Split:");
        for (String w : builtin) System.out.println(w);

        System.out.println("\nAre both same? " + compare(manual, builtin));
    }
}


