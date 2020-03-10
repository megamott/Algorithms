/*
Хеширование цепочками — один из наиболее популярных методов реализации
хеш-таблиц на практике.
Ваша цель в данной задаче — реализовать такую схему,
используя таблицу с m ячейками и полиномиальной хеш-функцией на строках.
Реализовать фкнкции:
• add string: добавить строку string в таблицу. Если такая
строка уже есть, проигнорировать запрос;
• del string: удалить строку string из таблицы. Если такой
строки нет, проигнорировать запрос;
• find string: вывести «yes» или «no» в зависимости от того,
есть в таблице строка string или нет;
• check i: вывести i-й список (используя пробел в качестве разделителя);
если i-й список пуст, вывести пустую строку.
При добавлении строки в цепочку, строка должна добавляться в начало цепочки.
Формат входа. Первая строка размер хеш-таблицы m. Следующая
строка содержит количество запросов n. Каждая из последующих n строк содержит запрос одного из перечисленных выше
четырёх типов.
Формат выхода. Для каждого из запросов типа find и check выведите результат в отдельной строке.
Вход:
5
12
add world
add HellO
check 4
find World
find world
del world
check 4
del HellO
add luck
add GooD
check 2
del good
Выход:
HellO world
no
yes
HellO
GooD luck
ASCII коды букв ’w’, ’o’, ’r’, ’l’, ’d’ равны 119, 111, 114, 108, 100,
соответственно.
Пример.
Вход:
4
8
add test
add test
find test
del test
find test
find Test
add Test
find Test
Выход:
yes
no
no
yes
*/


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

/**
 * Класс Хеш-таблица
 * Содержит массив листов строк
 * Размер этого массива
 * Константы p и x, необходимые для реализации хеш-функции
 * p в виде BigInteger
 */
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

    /**
     * Добавление слова
     * @param word - проверяемое слово
     */
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

    /**
     * Поиск слова
     * @param word - проверяемое слово
     */
    public void find(String word) {
        if ((table[MyHashCode(word, size)] != null) && (compareElements(word))) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    /**
     * Удаление слова
     * Проверяется на то, существует ли корзина с таким хешем
     * @param word - проверяемое слово
     */
    public void del(String word) {
        if (table[MyHashCode(word, size)] != null) {
            int myHashCode = MyHashCode(word, size);
            for (int i = 0; i < table[myHashCode].size(); i++) {
                if (table[myHashCode].get(i).equals(word)) table[myHashCode].remove(i);
            }
        }
    }

    /**
     * Вывод всех элментов корзины
     * Проверяется на то, существует ли корзина с таким хешем и не пуста ли она
     * В противном случае выаодится пустая строка
     * @param number - номер корзины
     */
    public void check(int number){
        if ((table[number] != null) && (table[number].size() != 0)){
            for (int i = 0; i < table[number].size(); i++) {
                System.out.print(table[number].get(i) + " ");
            }
            System.out.print("\n");
        }else System.out.println(" ");
    }

    /**
     * Сравнение элементов по их хеш-коду
     * @param word
     * @return
     */
    private boolean compareElements(String word) {
        int myHashCode = MyHashCode(word, size);
        for (int i = 0; i < table[myHashCode].size(); i++) {
            if (table[myHashCode].get(i).equals(word)) return true;
        }
        return false;
    }

    /**
     * Опрделение хеш функции слова
     * @param word
     * @param size - размер массива листов
     * @return
     */
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
        BigInteger newSize = BigInteger.valueOf(size);
        BigInteger resultHashModM = resultHashModP.mod(newSize);
        return resultHashModM.intValue();
    }

    /**
     * Взять элемент по модулю p
     * @param number
     * @return
     */
    private BigInteger mod(BigInteger number) {
        BigInteger var1 = number.mod(bigP);
        BigInteger var2 = var1.add(bigP);
        BigInteger result = var2.mod(bigP);
        return result;
    }
}

