import java.util.Scanner;
import java.util.Stack;

public class Week2MergedLab {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nChoose an option to run:");
            System.out.println("1. Substring Matcher");
            System.out.println("2. Infix/Prefix Converter");
            System.out.print("Enter 1 or 2: ");
            int choice = input.nextInt();
            input.nextLine(); // consume newline

            if (choice == 1) {
                runSubstringMatcher(input);
            } else if (choice == 2) {
                runInfixPrefixConverter(input);
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
                continue;
            }

            System.out.print("\nDo you want to try the other option? (yes/no): ");
            String response = input.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                keepRunning = false;
                System.out.println("Goodbye!");
            }
        }

        input.close();
    }

    // Substring Matcher
    public static void runSubstringMatcher(Scanner input) {
        System.out.print("\nEnter a string s1: ");
        String s1 = input.nextLine();
        System.out.print("Enter a string s2: ");
        String s2 = input.nextLine();

        int index = findSubstring(s1, s2);
        if (index != -1) {
            System.out.println("Matched at index " + index);
        } else {
            System.out.println("No match found");
        }
    }

    public static int findSubstring(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        if (m == 0) return 0;
        if (m > n) return -1;

        int i = 0;
        while (i <= n - m) {
            int j = 0;
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) return i;
            i += Math.max(j, 1);
        }
        return -1;
    }

    // Infix/Prefix Converter
    public static void runInfixPrefixConverter(Scanner input) {
        System.out.print("\nEnter an infix expression (e.g., (A+B)*C): ");
        String infix = input.nextLine();
        String prefix = infixToPrefix(infix);
        System.out.println("Prefix: " + prefix);

        System.out.print("\nEnter a prefix expression (e.g., *+ABC): ");
        String testPrefix = input.nextLine();
        String infixResult = prefixToInfix(testPrefix);
        System.out.println("Infix: " + infixResult);
    }

    public static String infixToPrefix(String infix) {
        StringBuilder reversed = new StringBuilder(infix).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (c == '(') reversed.setCharAt(i, ')');
            else if (c == ')') reversed.setCharAt(i, '(');
        }

        String postfix = infixToPostfix(reversed.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    private static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    public static String prefixToInfix(String prefix) {
        Stack<String> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op1 + c + op2 + ")");
            }
        }
        return stack.pop();
    }
}



//****output****

// Choose an option to run:
// 1. Substring Matcher
// 2. Infix/Prefix Converter
// Enter 1 or 2: 2

// Enter an infix expression (e.g., (A+B)*C): (A+B)*C
// Prefix: *+ABC

// Enter a prefix expression (e.g., *+ABC): *+ABC
// Infix: ((A+B)*C)

// Do you want to try the other option? (yes/no): yes

// Choose an option to run:
// 1. Substring Matcher
// 2. Infix/Prefix Converter
// Enter 1 or 2: 1

// Enter a string s1:
// Enter a string s2: hello
// No match found

// Do you want to try the other option? (yes/no): yes

// Choose an option to run:
// 1. Substring Matcher
// 2. Infix/Prefix Converter
// Enter 1 or 2: 1

// Enter a string s1: hello world
// Enter a string s2: hello
// Matched at index 0

// Do you want to try the other option? (yes/no): no
// Goodbye!

// === Code Execution Successful ===