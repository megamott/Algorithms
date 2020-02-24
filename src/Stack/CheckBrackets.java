package Stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Matvey
 */
public class CheckBrackets {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String brackets = scanner.nextLine();
        String brackets = "()[([ (    fsfjskl   )]]";
        System.out.println((check_brackets(brackets)));
    }

    private static String check_brackets(String string) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackCounter = new Stack<>();
        char[] elements = string.toCharArray();
        char top;
        int counter = 0;

        for (int i = 0; i < string.length(); i++) {
            if (elements[i] == '[' || elements[i] == '(') {
                stack.push(elements[i]);
                stackCounter.push(++counter);
            } else if (stack.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            } else if (elements[i] == ']') {
                if (stack.pop() != '[') throw new RuntimeException("Incorrect value for the bracket " + stackCounter.peek());
                stackCounter.pop();
            } else if (elements[i] == ')') {
                if (stack.pop() != '(') throw new RuntimeException("Incorrect value for the bracket " + stackCounter.peek());
                stackCounter.pop();
            }
        }
        if(stack.isEmpty()){return "Success!";}else throw new RuntimeException("Incorrect value for the bracket " + stackCounter.peek());
    }
}
