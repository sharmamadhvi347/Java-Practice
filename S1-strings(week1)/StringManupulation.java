public class  StringManupulation{
    public static void main (String[] args){
        String str1 = "Java Programming";
        String str2=  new String("Java Programming");
        char[] CharArray = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(CharArray);
        System.out.println("str1 == str2: " + (str1 == str2)); // false, different objects
        System.out.println("str1 == str3: " + (str1 == str3)); // false, different objects
        System.out.println("str2 == str3: " + (str2 == str3)); // false, different objects
        
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true, same content
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true, same content
        System.out.println("str2.equals(str3): " + str2.equals(str3)); // true, same content
         
        System.out.println("\nExplanation:");
        System.out.println("== compares references (memory addresses), .equals() compares actual content.");
        
        String quote = "Programming Quote:\n\t\"Code is poetry\" - Unknown\n\tPath: C:\\Java\\Projects";
        System.out.println("\n" + quote);
    }
}
