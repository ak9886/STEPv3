import java.util.*;

interface PalindromeStrategy {
    boolean check(String input);
}

class StackStrategy implements PalindromeStrategy {
    public boolean check(String input) {
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

class DequeStrategy implements PalindromeStrategy {
    public boolean check(String input) {
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : cleaned.toCharArray()) {
            deque.addLast(c);
        }
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }
}

class TwoPointerStrategy implements PalindromeStrategy {
    public boolean check(String input) {
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        int left = 0;
        int right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

public class UseCase13PalindromeCheckerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        PalindromeStrategy stackStrategy = new StackStrategy();
        PalindromeStrategy dequeStrategy = new DequeStrategy();
        PalindromeStrategy twoPointerStrategy = new TwoPointerStrategy();

        long start, end;

        start = System.nanoTime();
        boolean stackResult = stackStrategy.check(input);
        end = System.nanoTime();
        long stackTime = end - start;

        start = System.nanoTime();
        boolean dequeResult = dequeStrategy.check(input);
        end = System.nanoTime();
        long dequeTime = end - start;

        start = System.nanoTime();
        boolean twoPointerResult = twoPointerStrategy.check(input);
        end = System.nanoTime();
        long twoPointerTime = end - start;

        System.out.println("Stack Strategy Result: " + stackResult + " Time: " + stackTime + " ns");
        System.out.println("Deque Strategy Result: " + dequeResult + " Time: " + dequeTime + " ns");
        System.out.println("Two Pointer Strategy Result: " + twoPointerResult + " Time: " + twoPointerTime + " ns");
    }
}
