package stepik_algorithms_structures;

import java.util.Arrays;

/**
 * @author Matvey
 */
public class MinHeapBuilding {
    public static void main(String[] args) {
        int size = 4;
        int[] array = {5, 4, 3, 2, 1};
        HeapSort heapSort = new HeapSort();
        //Проверка
//        System.out.println(Arrays.toString(heapSort.sort(size, array)));
        heapSort.sort(size, array);
    }
}

class HeapSort {
    int[] sort(int size, int[] array) {
        for (int i = size; i > 0; i--) {
            siftDown(i, array);
        }
        return array;
    }


    private void siftDown(int i, int[] array) {
        while ((i > 0)&&(array[parent(i)]>array[i])) {
            System.out.print(parent(i));
            System.out.print(i + "\n");
            int var = array[i];
            array[i] = array[parent(i)];
            array[parent(i)] = var;

            i = parent(i);
        }
    }

    private int parent(int i) {
        if (i != 0) {
            return ((i - 1) / 2);
        } else throw new RuntimeException("Head element hasn't a parents");
    }

    private int leftChild(int i) {
        return (2 * i + 1);
    }

    private int rightChild(int i) {
        return (2 * i + 2);
    }
}

