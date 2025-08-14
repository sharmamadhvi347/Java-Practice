import java.util.Scanner;

public class VowelConsonantCount {

    
    static String checkCharType(char ch) {
        
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32); // 'A'(65) → 'a'(97)
        }

        
        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        }

        return "Not a Letter";
    }

    
    static int[] countVowelsAndConsonants(String str) {
        int vowels = 0, consonants = 0;

        
        for (int i = 0; i < str.length(); i++) {
            String type = checkCharType(str.charAt(i));
            if (type.equals("Vowel")) {
                vowels++;
            } else if (type.equals("Consonant")) {
                consonants++;
            }
        }

        return new int[]{vowels, consonants}; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        int[] counts = countVowelsAndConsonants(input);

        System.out.println("\nNumber of Vowels: " + counts[0]);
        System.out.println("Number of Consonants: " + counts[1]);
    }
}

