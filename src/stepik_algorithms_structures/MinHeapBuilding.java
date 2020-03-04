/*
Построение кучи — ключевой шаг алгоритма сортировки кучей. Данный алгоритм имеет время работы O(n log n) в худшем случае
в отличие от алгоритма быстрой сортировки, который гарантирует такую оценку только в среднем случае. Алгоритм быстрой
сортировки чаще используют на практике, поскольку в большинстве случаев он работает быстрее, но алгоритм сортировки кучей
используется для внешней сортировки данных, когда необходимо отсортировать данные огромного размера, не помещающиеся
в память компьютера.
Чтобы превратить данный массив в кучу, необходимо произвести несколько обменов его элементов. Обменом мы называем базовую
операцию, которая меняет местами элементы A[i] и A[j]. Ваша цель в данной задаче — преобразовать заданный массив
в кучу за линейное количество обменов.
Формат ввода.
Первая строка содержит число n. Следующая строка задает массив чисел A[0], ..., A[n - 1].
Формат вывода.
Первая строка выхода должна содержать число обменов m, которое должно удовлетворять неравенству 0 <= m <= 4n.
Каждая из последующих m строк должна задавать обмен двух элементов массива А. Каждый обмен задается парой различных индексов
0 <= i != j <= n-1. После применения всех обменов в указанном порядке массив должен превратиться в мин-кучу, то есть для всех
0 <= i <= n-1 должны выполняться следующие два условия:
	если 2i + 1 <= n-1, то A[i] < A[2i + 1],
	если 2i + 2 <= n-1, то A[i] < A[2i + 2].


Sample Input 1:
6
0 1 2 3 4 5
Sample Output 1:
0
Sample Input 2:
6
7 6 5 4 3 2
Sample Output 2:
4
2 5
1 4
0 2
2 5
*/
package stepik_algorithms_structures;

import java.util.Scanner;

/**
 * @author Matvey
 */
public class MinHeapBuilding {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        in.close();

//        Проверка
//        int size = 6;
//        int[] array = {7, 6, 5, 4, 3, 2};

        HeapSort heapSort = new HeapSort();
        heapSort.sort(size, array);
        System.out.println(heapSort.getCounter());
        System.out.println(heapSort.getList());
    }
}

class HeapSort {
    //Лист, хранящий результаты кажддой перестановки
    private StringBuilder list = new StringBuilder();
    //Счётчик перестановок
    private int counter = 0;

    StringBuilder getList() {
        return list;
    }

    int getCounter() {
        return counter;
    }

    /**
     *  ((size - 1)/ 2) потому, что все узлы ниже - листы
     *  Чинить свойства кучи у листов не нужно, так как их нет
     * @param size
     * @param array
     * @return
     */
    int[] sort(int size, int[] array) {
        for (int i = ((size - 1)/ 2); i >= 0; i--) {
            siftDown(i, array, size);
        }
        return array;
    }


    /**
     * Алгоритм просеивания вниз
     * @param i
     * @param array
     * @param size
     */
    private void siftDown(int i, int[] array, int size) {
//        Реализация метода siftUp для дальнейшего использования
//        while ((i > 0)&&(array[parent(i)]>array[i])) {
//            this.list.append(parent(i) + " " + i + "\n");
//            this.counter++;
//
//            int var = array[i];
//            array[i] = array[parent(i)];
//            array[parent(i)] = var;
//
//            i = parent(i);
        int left = leftChild(i);
        int right = rightChild(i);
        int minIndex = i;

        if ((left < size)&&(array[left] < array[minIndex])) {
            minIndex = left;
        }
        if ((right < size)&&(array[right] < array[minIndex])) {
            minIndex = right;
        }
        if (i != minIndex) {
            this.list.append(i + " " + minIndex + "\n");
            this.counter++;

            int var = array[i];
            array[i] = array[minIndex];
            array[minIndex] = var;
            siftDown(minIndex, array, size);
        }
    }

//    Реализация метода получения родителя узла для дальнейшего использования
//    private int parent(int i) {
//        if (i != 0) {
//            return ((i - 1) / 2);
//        } else throw new RuntimeException("Head element hasn't a parents");
//    }

    /**
     * Получение левого ребёнка узла
     * @param i
     * @return
     */
    private int leftChild(int i) {
        return (2 * i + 1);
    }

    /**
     * Получение правого ребёнка узла
     * @param i
     * @return
     */
    private int rightChild(int i) {
        return (2 * i + 2);
    }
}

