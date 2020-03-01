package stepik_algorithms_structures;

import java.util.*;

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

        for (int i = 0; i < number; ++i) {
            if (!window.isEmpty() && i >= windowSize) {
                int x = window.peek();
                System.out.println(x);
                if (x == array[i-windowSize]) {
                    window.poll();
                }
            }

            while (!window.isEmpty() && window.peekFirst() < array[i]) {
                window.removeLast();
            }
            window.addLast(array[i]);
        }
        System.out.println(window.peekFirst());

    }
}
