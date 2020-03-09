package stepik_algorithms_structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Matvey
 */
public class ChainingHashTable {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(5);
//        hashTable.add("world");
    }
}

class HashTable{
    private List<String>[] table;
    private int size;
    private final long p = 1000000007;
    private final int x = 263;

    public HashTable(int size) {
        this.size = size;
        this.table = new ArrayList[size];
    }

    public List<String>[] getTable() {
        return table;
    }

    public void add(String word){
        int myHashCode = MyHashCode(word, size);
        this.table[myHashCode].add(word);
    }

    private int MyHashCode(String word, int size){
        long hash = 0;
        int length = word.length()-1;
        while (length != 0){
            hash += mod((word.charAt(length)*(long)Math.pow(x, length)));
            length--;
        }
        hash += mod((word.charAt(0)));
        return (int)(hash%p)%size;
    }

    private long mod(long number){
        return (number%p + p)%p;
    }
}

