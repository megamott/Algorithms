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
