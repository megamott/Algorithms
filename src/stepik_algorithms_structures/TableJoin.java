package stepik_algorithms_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Matvey
 */
public class TableJoin {
    public static void main(String[] args) {

//        int requestsNumber = 6;
//        int tableNumber = 4;
//        int[] tableSizes = {10, 0, 5, 0, 3, 3};

        Scanner in = new Scanner(System.in);
        int tableNumber = in.nextInt();
        int requestsNumber = in.nextInt();
        int[] tableSizes = new int[tableNumber];
        for (int i = 0; i < tableNumber; i++) {
            tableSizes[i] = in.nextInt();
        }
        DataBase dataBase = new DataBase(tableNumber, tableSizes);
        for (int i = 0; i < requestsNumber; i++) {
            dataBase.Union(in.nextInt() - 1, in.nextInt() - 1);
            System.out.println(dataBase.getHeights());
            System.out.println(dataBase.getMaxHeight());
        }
        in.close();
    }
}

class DataBase {
    private int tableNumber;
    private List<Table> tables = new ArrayList<>();
    private List<Integer> heights = new ArrayList<>();

    public DataBase(int tableNumber, int[] tableSizes) {
        this.tableNumber = tableNumber;
        for (int i = 0; i < tableNumber; i++) {
            tables.add(new Table(tableSizes[i]));
            heights.add(tables.get(i).getSize());
        }
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Integer> getHeights() {
        return heights;
    }

    public void Union(int to, int from) {
        Table tableTo = tables.get(to).findParent(tables.get(to));
        Table tableFrom = tables.get(from).findParent(tables.get(from));
        if (tableFrom != tableTo) {
            tableFrom.setParent(tableTo);
        }
        if (tables.get(to).equals(tables.get(to).getParent())) {
            heights.set(to, tables.get(from).getSize());
        } else {
//            tables.get(from).findParent(tables.get(from))
//                    .setParent(tables.get(to).findParent(tables.get(to)));
//            heights.set(to, tables.get(from).findParent(tables.get(from)).getSize());
        }
    }

    public int getMaxHeight() {
        int max = 0;
        for (int i = 0; i < tableNumber; i++) {
            if (heights.get(i) > max) max = heights.get(i);
        }
        return max;
    }
}

class Table {
    private int size;
    private Table parent;

    public Table(int size) {
        this.size = size;
        this.parent = this;
    }

    public int getSize() {
        if (parent != this) {
            return size + parent.getSize();
        } else
            return size;
    }

    public void setParent(Table parent) {
        this.parent = parent;
    }

    public Table getParent() {
        return parent;
    }

    public Table findParent(Table table) {
        if (table != table.getParent()) {
            table = findParent(table.getParent());
        }
        return table;
    }
}