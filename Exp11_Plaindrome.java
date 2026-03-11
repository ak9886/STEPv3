import java.util.Stack;
import java.util.Scanner;

class PalindromeChecker {
    public boolean checkPalindrome(String input) {
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        Stack<Character> stack = new Stack<>();
        for (char c : cleaned.toCharArray()) {
            stack.push(c);
        }
        String reversed = "";
        while (!stack.isEmpty()) {
            reversed += stack.pop();
        }
        return cleaned.equals(reversed);
    }
}

public class UseCase11PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PalindromeChecker checker = new PalindromeChecker();
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        boolean result = checker.checkPalindrome(input);
        if (result) {
            System.out.println("The given string is a palindrome.");
        } else {
            System.out.println("The given string is not a palindrome.");
        }
    }
}
