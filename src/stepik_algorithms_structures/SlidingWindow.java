/*
Найти максимум в каждом окне размера m данного массива чисел A[1..n].
Наивный способ решить данную задачу — честно просканировать каждое окно и найти в нем максимум. Время работы такого
алгоритма — O(nm). Ваша задача — реализовать алгоритм со временем работы O(n).
Формат ввода.
Первая строка входа содержит число n, вторая — массив A[1..n], третья — число m.
Формат вывода.
n - m + 1 максимумов, разделенных пробелами.
Sample Input 1:
3
2 1 5
1
Sample Output 1:
2 1 5
Sample Input 2:
8
2 7 3 1 5 2 6 2
4
Sample Output 2:
7 7 5 6 6
*/

package stepik_algorithms_structures;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Matvey
 */
public class SlidingWindow {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = in.nextInt();
        }
        int windowSize = in.nextInt();
        in.close();

        Deque<Integer> window = new LinkedList<>();

        for (int i = 0; i < number; i++) {
            if (!window.isEmpty() && i >= windowSize) {
                int x = window.peek();
                System.out.println(x);
                if (x == array[i-windowSize]) {
                    window.poll();
                }
            }

            while (!window.isEmpty() && window.peekLast() < array[i]) {
                window.removeLast();
            }
            window.addLast(array[i]);
        }

        System.out.println(window.peekFirst());
    }
}
