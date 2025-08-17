public class TrimSpaces {

    
    public static int[] findTrimIndexes(String str) {
        int start = 0;
        int end = str.length() - 1;

        
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }

        
        while (end >= 0 && str.charAt(end) == ' ') {
            end--;
        }

        return new int[]{start, end};
    }

    
    public static String customSubstring(String str, int start, int end) {
        String result = "";
        for (int i = start; i <= end; i++) {
            result += str.charAt(i);
        }
        return result;
    }

    
    public static boolean customCompare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "    Hello World!   ";

        
        int[] indexes = findTrimIndexes(input);

        
        String manuallyTrimmed = "";
        if (indexes[0] <= indexes[1]) {
            manuallyTrimmed = customSubstring(input, indexes[0], indexes[1]);
        }

       
        String builtInTrimmed = input.trim();

        
        boolean isSame = customCompare(manuallyTrimmed, builtInTrimmed);

        
        System.out.println("Original String: '" + input + "'");
        System.out.println("Manually Trimmed: '" + manuallyTrimmed + "'");
        System.out.println("Built-in Trimmed: '" + builtInTrimmed + "'");
        System.out.println("Are both same? " + isSame);
    }
}

