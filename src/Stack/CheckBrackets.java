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
        String brackets = "foo(bari)";
        System.out.println((check_brackets(brackets)));
    }

    private static String check_brackets(String string) {
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackCounter = new Stack<>();
        char[] elements = string.toCharArray();
        int top = 0;
        int counter = 0;

        for (int i = 0; i < string.length(); i++) {
            counter++;
            if (elements[i] == '[' || elements[i] == '(') {
                top++;
                stack.push(elements[i]);
                stackCounter.push(top);
            } else if (elements[i] == ']') {
                if (stack.isEmpty()) {
                    throw new RuntimeException("The ']' bracket number " + counter + " doesn't have a pair");
                } else if (stack.peek() == '[') {
                    stack.pop();
                    stackCounter.pop();
                }else{ throw new RuntimeException("The " + stack.peek() + " bracket number " + stackCounter.peek() + " doesn't have a pair");}
            }  else if (elements[i] == ')'){if (stack.isEmpty()) {
                throw new RuntimeException("The ')' bracket number " + counter + " doesn't have a pair");
            } else if (stack.peek() == '(') {
                stack.pop();
                stackCounter.pop();
            }else{ throw new RuntimeException("The " + stack.peek() + " bracket number " + stackCounter.peek() + " doesn't have a pair");}}
        }
        if(top == 0) throw new RuntimeException("No brackets!");
        if(stack.isEmpty()){return "Success";}else throw new RuntimeException("The '" + stack.peek() + "' bracket number " + stackCounter.peek() + " doesn't have a pair");
    }
}
