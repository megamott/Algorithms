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
                String word = in.nextLine();
                hashTable.add(word.substring(1));
            }else if(command.equals("find")){
                String word = in.nextLine();
                hashTable.find(word.substring(1));
            }else if(command.equals("del")){
                String word = in.nextLine();
                hashTable.del(word.substring(1));
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
    private final long x = 263;
    private BigInteger bigP;

    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        this.bigP = BigInteger.valueOf(p);
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
            BigInteger var1 = BigInteger.valueOf((word.charAt(length)));
            BigInteger varSpace1 = mod(var1);
            BigInteger var2 = BigInteger.valueOf((long) Math.pow(x, length));
            BigInteger varSpace2 = mod(var2);
            BigInteger var3 = varSpace1.multiply(varSpace2);
            BigInteger var4 = mod(var3);
            hash += var4.intValue();
            length--;
        }
        BigInteger lastElement = BigInteger.valueOf((word.charAt(0)));
        BigInteger resultLastElement = mod(lastElement);
        BigInteger myHash = BigInteger.valueOf(hash);
        BigInteger resultHash = myHash.add(resultLastElement);
        BigInteger resultHashModP = mod(resultHash);
        BigInteger newSize = BigInteger.valueOf((long)size);
        BigInteger resultHashModM = resultHashModP.mod(newSize);
        return resultHashModM.intValue();
    }

    private BigInteger mod(BigInteger number) {
        BigInteger var1 = number.mod(bigP);
        BigInteger var2 = var1.add(bigP);
        BigInteger result = var2.mod(bigP);
        return result;
    }
}

