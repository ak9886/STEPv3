import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.println("Enter a String: ");
        Scanner input = new Scanner(System.in);
        String word = input.nextLine();
        System.out.println("" + word);


        String reversed = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversed = reversed + word.charAt(i);
        }


        if (word.equals(reversed)) {
            System.out.println(word + " is a Palindrome.");
        } else {
            System.out.println(word + " is not a Palindrome.");
        }
    }
}

