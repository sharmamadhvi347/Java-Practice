import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a your full name ");
        String name = scanner.nextLine();

        System.out.println("Enter your fav prog. lang: ");
        String lang = scanner.nextLine();

        System.out.println("A sentence about their programming exp: ");
        String sentence = scanner.nextLine();

        String[] nameParts = name.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        // Count total characters in sentence (excluding spaces)
        int charCount = sentence.replace(" ", "").length();

        // Convert programming language to uppercase
        String favLangUpper = lang.toUpperCase();

        // Display formatted summary
        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Programming Language (Uppercase): " + favLangUpper);
        System.out.println("Characters in your sentence (excluding spaces): " + charCount);
        System.out.println("Your sentence: \"" + sentence + "\"");

        scanner.close();

    }
}
