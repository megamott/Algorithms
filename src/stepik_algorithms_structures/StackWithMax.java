package stepik_algorithms_structures;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Matvey
 */
public class StackWithMax {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        Scanner in = new Scanner(System.in);
        int counter = in.nextInt();
        String bag = in.nextLine();
        for (int i = 0; i < counter; i++) {
            String request = in.nextLine();
            if (request.equals("pop")) {
                stack.pop();
            } else if (request.equals("max")) {
                stack.max();
            } else if (request.contains("push")) {
                stack.push(Integer.parseInt(request.split(" ")[1]));
            } else continue;
        }
    }
}

class MyStack extends Stack<Integer> {
    private Stack<Integer> maxElements = new Stack<>();

    MyStack() {
        super();
    }

    @Override
    public Integer push(Integer item) {
        if (!this.isEmpty()) {
            if (this.peek() < item) {
                this.maxElements.push(item);
            } else {
                this.maxElements.push(maxElements.peek());
            }
        } else this.maxElements.push(item);
        return super.push(item);
    }

    @Override
    public synchronized Integer pop() {
        maxElements.pop();
        return super.pop();
    }

    void max() {
        System.out.println(maxElements.peek());
    }
}
