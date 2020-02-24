/*
Вы разрабатываете текстовый редактор для программистов и хотите реализовать проверку корректности расстановки скобок.
В коде могут встречаться скобки []{}(). Из них скобки [,{ и ( считаются открывающими, а соответствующими им
закрывающими скобками являются ],} и ).
В случае, если скобки расставлены неправильно, редактор должен также сообщить пользователю первое место, где обнаружена
ошибка.
В первую очередь необходимо найти закрывающую скобку, для которой либо нет соответствующей открывающей (например, скобка ]
в строке “]()”), либо же она закрывает не соответствующую ей открывающую скобку (пример: “()[}”).
Если таких ошибок нет, необходимо найти первую открывающую скобку, для которой нет соответствующей закрывающей
(пример: скобка ( в строке “{}([]”). Помимо скобок, исходный код может содержать символы латинского алфавита,
цифры и знаки препинания.
Формат ввода.
Строка s[1...n], состоящая из заглавных и прописных букв латинского алфавита, цифр,
знаков препинания и скобок из множества []{}().
Формат вывода.
Если скобки в s расставлены правильно, выведите строку “Success". В противном случае выведите индекс (используя
индексацию с единицы) первой закрывающей скобки, для которой нет соответствующей открывающей.
Если такой нет,выведите индекс первой открывающей скобки, для которой нет соответствующей закрывающей.
Sample Input 1:
([](){([])})
Sample Output 1:
Success
Sample Input 2:
()[]}
Sample Output 2:
5
Sample Input 3:
{{[()]]
Sample Output 3:
7
Sample Input 4:
foo(bar[i);
Sample Output 4:
10
Sample Output 5:
(((
Sample Output 5:
1
*/
package stepik_algorithms_structures;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Matvey
 */
public class Brackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String brackets = scanner.nextLine();
        char[] symbols = brackets.toCharArray();
        Stack<Character> stack = new Stack<>();
        int counter = 0;
        LinkedList<Integer> close = new LinkedList<>();
        LinkedList<Integer> open = new LinkedList<>();

        for (char c : symbols) {
            counter++;
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
                open.add(counter);
            } else if (c == ']' || c == ')' || c == '}') {
                if ((!stack.isEmpty()) && ((c == ']' && stack.peek() == '[')
                        || (c == '}' && stack.peek() == '{')
                        || (c == ')' && stack.peek() == '('))) {
                    stack.pop();
                    open.removeLast();
                } else {
                    close.add(counter);
                    break;
                }
            }
        }
        if (!close.isEmpty()) {
            System.out.println(close.getLast());
        } else if (!open.isEmpty()) {
            System.out.println(open.getLast());
        } else System.out.println("Success");
    }
}
