import java.util.Scanner;

public class Main{

    public static boolean isPalindrome(String input) {
        String filtered = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return checkRecursive(filtered, 0, filtered.length() - 1);
    }

    public static boolean checkRecursive(String str, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return checkRecursive(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string to check if it is a palindrome:");
        String input = scanner.nextLine();

        if (isPalindrome(input)) {
            System.out.println("The given string is a Palindrome.");
        } else {
            System.out.println("The given string is NOT a Palindrome.");
        }

        scanner.close();
    }
}

