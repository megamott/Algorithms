package stepik_algorithms_structures;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Matvey
 */
public class networkPackage {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int number = in.nextInt();
        Buffer buffer = new Buffer(size);
        Processor processor = new Processor();

        processor.operating(buffer, number, size, in);
    }
}

class Buffer {
    private int size;
    private Queue<Integer> stock;

    public Buffer(int size) {
        this.size = size;
        this.stock = new ArrayBlockingQueue<>(size);
    }

    public Queue<Integer> getStock() {
        return stock;
    }

    void addToBuffer(int cpuTime) {
        stock.add(cpuTime);
    }
}

class Processor {
    private int cpuTime;

    void operating(Buffer buffer, int number, int size, Scanner in) {
        for (int i = 0; i < number; i++) {
            int arrival = in.nextInt();
            int duration = in.nextInt();

            while (!buffer.getStock().isEmpty() && buffer.getStock().peek() <= arrival) {
                buffer.getStock().poll();
            }

            if (cpuTime < arrival) {
                cpuTime = arrival;
                System.out.println(cpuTime);
                buffer.addToBuffer(cpuTime + duration);
            } else if (buffer.getStock().size() < size) {
                System.out.println(cpuTime);
                cpuTime += duration;
                buffer.addToBuffer(cpuTime);
            } else {
                System.out.println(-1);
            }
        }

    }
}
