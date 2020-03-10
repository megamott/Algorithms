package stepik_algorithms_structures;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Matvey
 */
public class ChainingHashTable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashTable hashTable = new HashTable(in.nextInt());
        int commandNumber = in.nextInt();
        for (int i = 0; i < commandNumber; i++) {
            String command = in.next();
            if (command.equals("add")){
                String word = in.next();
                hashTable.add(word);
            }else if(command.equals("find")){
                String word = in.next();
                hashTable.find(word);
            }else if(command.equals("del")){
                String word = in.next();
                hashTable.del(word);
            }else if(command.equals("check")){
                hashTable.check(in.nextInt());
            }
        }
    }
}

class HashTable {
    private LinkedList[] table;
    private int size;
    private final long p = 1000000007;
    private final int x = 263;

    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
    }

    public LinkedList[] getTable() {
        return table;
    }

    public void add(String word) {
        int myHashCode = MyHashCode(word, size);
        if (table[myHashCode] == null) {
            LinkedList<String> list = new LinkedList<>();
            list.addFirst(word);
            table[myHashCode] = list;
        } else {
            if (!compareElements(word)) table[myHashCode].addFirst(word);
        }
    }

    public void find(String word) {
        if ((table[MyHashCode(word, size)] != null) && (compareElements(word))) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public void del(String word) {
        if (table[MyHashCode(word, size)] != null) {
            int myHashCode = MyHashCode(word, size);
            for (int i = 0; i < table[myHashCode].size(); i++) {
                if (table[myHashCode].get(i).equals(word)) table[myHashCode].remove(i);
            }
        }
    }

    public void check(int number){
        if ((table[number] != null) && (table[number].size() != 0)){
            for (int i = 0; i < table[number].size(); i++) {
                System.out.print(table[number].get(i) + " ");
            }
            System.out.print("\n");
        }else System.out.println(" ");
    }

    private boolean compareElements(String word) {
        int myHashCode = MyHashCode(word, size);
        for (int i = 0; i < table[myHashCode].size(); i++) {
            if (table[myHashCode].get(i).equals(word)) return true;
        }
        return false;
    }

    private int MyHashCode(String word, int size) {
        long hash = 0;
        int length = word.length() - 1;
        while (length != 0) {
            hash += mod((word.charAt(length) * (long) Math.pow(x, length)));
            length--;
        }
        hash += mod((word.charAt(0)));
        return (int) (hash % p) % size;
    }

    private long mod(long number) {
        return (number % p + p) % p;
    }
}

