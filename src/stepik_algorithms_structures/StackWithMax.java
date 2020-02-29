/*
Стек — абстрактная структура данных, поддерживающая операции push и pop.
Несложно реализовать стек так, чтобы обе эти операции работали за константное время.
В данной задача ваша цель — расшить интерфейс стека так, чтобы он дополнительно поддерживал операцию max
и при этом чтобы время работы всех операций по-прежнему было константным.
Формат ввода.
Первая строка содержит число запросов q. Каждая из последующих q строк задает запрос в одном из следующих
форматов: push v, pop, или max.
Формат вывода.
Для каждого запроса max выведите (в отдельной строке) текущий максимум на стеке.
Sample Input 1:
5
push 2
push 1
max
pop
max
Sample Output 1:
2
2
Sample Input 2:
5
push 1
push 2
max
pop
max
Sample Output 2:
2
1
Sample Input 3:
10
push 2
push 3
push 9
push 7
push 2
max
max
max
pop
max
Sample Output 3:
9
9
9
9
*/
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
