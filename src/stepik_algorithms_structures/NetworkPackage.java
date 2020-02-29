/*
Ваша цель - реализовать симулятор обработки сетевых пакетов. Для i-го пакета известно время его поступления arrival[i],
а также время duration[i], необходимое на его обработку.
В вашем распоряжении имеется один процессор, который обрабатывает пакеты в порядке их поступления. Если процессор
начинает обрабатывать пакет i (что занимает время duration[i]), он не прерывается и не останавливается до тех пор,
пока не обработает пакет.
У компьютера, обрабатывающего пакеты, имеется сетевой буфер размера size. До начала обработки пакеты хранятся в буфере.
Если буфер полностью заполнен в момент поступления пакета (есть size пакетов, поступивших ранее, которые до сих пор
не обработаны), этот пакет отбрасывается и уже не будет обработан. Если несколько пакетов поступает в одно и то же время,
они все будут сперва сохранены в буфер (несколько последних из них могут быть отброшены, если буфер заполнится).
Компьютер обрабатывает пакеты в порядке их поступления. Он начинает обрабатывать следующий пакет из буфера сразу после того,
как обработает текущий пакет. Компьютер может простаивать, если все пакеты уже обработаны и в буфере нет пакетов.
Пакет освобождает место в буфере сразу же, как компьютер заканчивает его обработку.
Формат ввода.
Первая строка входа содержит размера буфера size и число пакетов n.
Каждая из следующих n строк содержит два числа: время arrival[i] прибытия i-го пакета и время duration[i],
необходимое на его обработку.
Гарантируется, что arrival[1] <= arrival[2] <= ... <= arrival[n].
При этом может оказаться, что arrival[i-1] = arrival[i]. В таком случае считаем, что пакет i-1 поступил раньше пакета i.
Формата вывода.
Для каждого из n пакетов выведите время, когда процессор начал его обрабатывать, или -1, если пакет был отброшен.
Sample Input 1:
1 0
Sample Output 1:
(если пакетов нет, ничего не выводится)
Sample Input 2:
1 1
0 0
Sample Output 2:
0
Sample Input 3:
1 1
0 1
Sample Output 3:
0
*/
package stepik_algorithms_structures;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Matvey
 */
public class NetworkPackage {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int number = in.nextInt();
        Buffer buffer = new Buffer(size);
        Processor processor = new Processor();

        processor.operating(buffer, number, size, in);
    }
}

/**
 * Класс буфера
 */
class Buffer {
    private Queue<Integer> stock;

    Buffer(int size) {
        this.stock = new ArrayBlockingQueue<>(size);
    }

    Queue<Integer> getStock() {
        return stock;
    }

    /**
     * Добавление в буфер суммарного времени прибытия и обработки пакета
     * @param cpuTime
     */
    void addToBuffer(int cpuTime) {
        stock.add(cpuTime);
    }
}

/**
 * Класс процессор, исполняющий алгоритм обратботки пакетов
 */
class Processor {
    private int cpuTime;

    /**
     * Работа процессора
     * @param buffer
     * @param number
     * @param size
     * @param in
     */
    void operating(Buffer buffer, int number, int size, Scanner in) {
        for (int i = 0; i < number; i++) {
            int arrival = in.nextInt();
            int duration = in.nextInt();

            //Пакет пришёл во время большее времени обработки последнего пакета
            while (!buffer.getStock().isEmpty() && buffer.getStock().peek() <= arrival) {
                buffer.getStock().poll();
            }

            //Пакет пришёл во время того, как процессор не работал
            if (cpuTime < arrival) {
                cpuTime = arrival;
                System.out.println(cpuTime);
                buffer.addToBuffer(cpuTime + duration);
            } else if (buffer.getStock().size() < size) {
                System.out.println(cpuTime);
                cpuTime += duration;
                buffer.addToBuffer(cpuTime);
                //Буфер полностью заполнен
            } else {
                System.out.println(-1);
            }
        }

    }
}
