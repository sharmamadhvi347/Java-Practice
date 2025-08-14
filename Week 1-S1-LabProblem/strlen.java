import java.util.Scanner;

public class Strlen{
		public static int getStringlength(String str) {
			int count = 0;
			try {
				while(true){
					str.charAt(count);
					count++;
				}
			}catch(IndexOutOfBoundsException e){
			}
			return count;
		}
		public static void main(String[] args){
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter a string:");
			String input = scanner.next();
			int newlength = getStringlength(input);
			int builtInLength=input.length();
			System.out.println("calculate length:"+newlength);
			System.out.println("calculate length using length():"+builtInLength);
		}
}

