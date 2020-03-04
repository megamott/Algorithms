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
    private StringBuilder list = new StringBuilder();
    private int counter = 0;

    public StringBuilder getList() {
        return list;
    }

    public int getCounter() {
        return counter;
    }

    int[] sort(int size, int[] array) {
        for (int i = ((size - 1)/ 2); i >= 0; i--) {
            siftDown(i, array, size);
        }
        return array;
    }


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

    private int leftChild(int i) {
        return (2 * i + 1);
    }

    private int rightChild(int i) {
        return (2 * i + 2);
    }
}

