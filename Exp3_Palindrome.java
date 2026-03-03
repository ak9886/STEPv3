import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Enter a string to check whether it is a palindrome:");
        String originalString = scanner.nextLine();

      
        String reversedString = "";

        
        for (int i = originalString.length() - 1; i >= 0; i--) {
            reversedString = reversedString + originalString.charAt(i);
        }

       
        if (originalString.equals(reversedString)) {
            System.out.println("The given string is a Palindrome.");
        } else {
            System.out.println("The given string is NOT a Palindrome.");
        }

        
        scanner.close();
    }
}

