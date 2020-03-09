/*
Цель в данной задаче — реализовать простую телефонную книгу, поддерживающую три следующих типа запросов.
С указанными ограничениями данная задача может быть решена с использованием таблицы с прямой адресацией.
add number name: добавить запись с именем name и телефонным номером number. Если запись с таким телефонным номером
уже есть, нужно заменить в ней имя на name.
del number: удалить запись с соответствующим телефонным номером. Если такой записи нет, ничего не делать.
find number: найти имя записи с телефонным номером number. Если запись с таким номером есть, вывести имя.
В противном случае вывести «not found» (без кавычек).
Формат ввода.
Первая строка содержит число запросов n. Каждая из следующих n строк задает запрос в одном из трех описанных выше форматов.
Формат вывода.
Для каждого запроса find выведите в отдельной строке либо имя, либо «not found».
Ограничения: 1 <= n <= 10^5. Телефонные номера содержат не более семи цифр и не содержат ведущих нулей. Имена содержат
только буквы латинского алфавита, не являются пустыми строками и имеют длину не больше 15. Гарантируется, что среди имен
не встречается строка "not found".
Sample Input 1:
12
add 911 police
add 76213 Mom
add 17239 Bob
find 76213
find 910
find 911
del 910
del 911
find 911
find 76213
add 76213 daddy
find 76213
Sample Output 1:
Mom
not found
police
not found
Mom
daddy
Sample Input 2:
8
find 3839442
add 123456 me
add 0 granny
find 0
find 123456
del 0
del 0
find 0
Sample Output 2:
not found
granny
me
not found
*/
package stepik_algorithms_structures;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Matvey
 */
public class TelephoneBook {
    public static void main(String[] args) {
        Book telephoneBook = new Book();
        Scanner in = new Scanner(System.in);
        int quantity = in.nextInt();
        for (int i = 0; i < quantity; i++) {
            String command = in.next();
            if (command.equals("add")) {
                telephoneBook.add(in.nextInt(), in.nextLine());
            }else if(command.equals("find")){
                telephoneBook.find(in.nextInt());
            }else if(command.equals("del")){
                telephoneBook.del(in.nextInt());
            }
        }
    }
}

/**
 * Можно было реализовать по-другому
 * На вход запроса подавать entry
 * В массиве хранить не entry, а только строки по индексу равному номеру
 * Массив должен быть размера 1000000000
 */
class Book {
    private Map<Integer, String> myBook = new HashMap<>();

    public void add(int number, String contact) {
        myBook.put(number, contact);
    }

    public void find(int number) {
        if (myBook.containsKey(number)) {
            System.out.println(myBook.get(number));
        } else {
            System.out.println("not found");
        }
    }

    public void del(int number) {
        if (myBook.containsKey(number)) {
            myBook.remove(number);
        }
    }
}
