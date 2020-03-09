/*
Ваша цель в данной задаче — реализовать симуляцию объединения
таблиц в базе данных.
В базе данных есть n таблиц, пронумерованных от 1 до n, над одним и тем же множеством столбцов (атрибутов).
Каждая таблица содержит либо реальные записи в таблице, либо символьную ссылку на
другую таблицу. Изначально все таблицы содержат реальные записи,
и i-я таблица содержит ri записей. Ваша цель — обработать m запросов типа (destinationi
, sourcei):
1. Рассмотрим таблицу с номером destinationi
. Пройдясь по цепочке символьных ссылок, найдём номер реальной таблицы, на которую ссылается эта таблица:
пока таблица destinationi содержит символическую ссылку:
destinationi ← symlink(destinationi)
2. Сделаем то же самое с таблицей sourcei
.
3. Теперь таблицы destinationi и sourcei содержат реальные записи. Если destinationi 6= sourcei
, скопируем все записи из таблицы
sourcei в таблицу destinationi
, очистим таблицу sourcei и пропишем в неё символическую ссылку на таблицу destinationi
.
4. Выведем максимальный размер среди всех n таблиц. Размером
таблицы называется число строк в ней. Если таблица содержит
символическую ссылку, считаем её размер равным нулю.
Формат входа. Первая строка содержит числа n и m — число таблиц
и число запросов, соответственно. Вторая строка содержит n целых чисел r1, . . . , rn — размеры таблиц.
Каждая из последующих m строк содержит два номера таблиц destinationi и sourcei, которые необходимо объединить.
Формат выхода. Для каждого из m запросов выведите максимальный размер таблицы после соответствующего объединения.
23
Ограничения. 1 ≤ n, m ≤ 100 000; 0 ≤ ri ≤ 10 000;
1 ≤ destinationi, sourcei ≤ n.
Пример.
Вход:
5 5
1 1 1 1 1
3 5
2 4
1 4
5 4
5 3
Выход:
2
2
3
5
5
Изначально каждая таблица содержит ровно одну строку.
1. После первой операции объединения все записи из таблицы 5 копируются в таблицу 3. Теперь таблица 5 является
ссылкой на таблицу 3, а таблица 3 содержит две записи.
2. Вторая операция аналогичным образом переносит все записи из таблицы 2 в таблицу 4.
3. Третья операция пытается объединить таблицы 1 и 4, но
таблица 4 ссылается на таблицу 2, поэтому все записи из
таблицы 2 копируются в таблицу 1. Таблица 1 теперь содержит три строки.
4. Чтобы произвести четвёртую операцию, проследим пути
из ссылок: 4 → 2 → 1 и 5 → 3. Скопируем все записи из
таблицы 1 в таблицу 3, после чего в таблице 3 будет пять
записей.
5. После этого все таблицы ссылаются на таблицу 3, поэтому
все оставшиеся запросы объединения ничего не меняют.
24
Пример.
Данный пример проверку не проходит, так как я считаю его нелогичным
Вход:
6 4
10 0 5 0 3 3
6 6
6 5
5 4
4 3
Выход:
10
10
10
11
1. Запрос объединения таблицы 6 с собой ничего не меняет,
максимальным размером по-прежнему остаётся 10 (таблица 1).
2. Записи из таблицы 5 копируются в таблицу 6, размер таблицы 6 становится равным 6.
3. Записи из таблицы 4 копируются в таблицу 6, размер таблицы 6 становится равным 10.
4. Записи из таблицы 3 копируются в таблицу 6, размер таблицы 6 становится равным 11.
 */

package stepik_algorithms_structures; // Удалить, если использовать, как отдельный файл

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
//            Проверка
//            System.out.println(dataBase.getHeights());
            System.out.println(dataBase.getMaxHeight());
        }
        in.close();
    }
}

/**
 * База данных
 * Содержит массив таблиц
 * Содержит массив высот(размеров таблиц)
 * Содержит количество таблиц
 */
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

    /**
     * Объединение таблиц
     * Проверяет на то, не являются соединяемые таблицы одной и той же
     * Проверяет на то, родитель таблицы она сама, или у неё уже есть иной родитель(иная таблица)
     * В зависимости от этого записывает в таблицу высот новую высоту(размер таблицы)
     * @param to
     * @param from
     */
    public void Union(int to, int from) {
        Table tableTo = tables.get(to).findParent(tables.get(to));
        Table tableFrom = tables.get(from).findParent(tables.get(from));
        if (tableFrom != tableTo) {
            if (tables.get(to).equals(tables.get(to).getParent())) {
                tableFrom.setParent(tableTo);
                heights.set(to, tables.get(from).getSize());
            } else {
                heights.set(to, tables.get(from).getSize() + tables.get(to).getSize());
                tableFrom.setParent(tableTo);
            }
        }
    }

    /**
     * Проходит по массиву высот(размеров таблиц) и находит максимальный элемент
     * @return
     */
    public int getMaxHeight() {
        int max = 0;
        for (int i = 0; i < tableNumber; i++) {
            if (heights.get(i) > max) max = heights.get(i);
        }
        return max;
    }
}

/**
 * Класс таблицы
 * Содержит размер таблицы
 * Содержит ссылку на таблицу-родителя
 */
class Table {
    private int size;
    private Table parent;

    public Table(int size) {
        this.size = size;
        this.parent = this;
    }

    /**
     * Получение размера таблицы
     * Если у таблицы есть родитель(другая таблица), то возвращает сумму размеров этих таблиц
     * @return
     */
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

    /**
     * Рекурсивный поиск родителя таблицы
     * @param table
     * @return
     */
    public Table findParent(Table table) {
        if (table != table.getParent()) {
            table = findParent(table.getParent());
        }
        return table;
    }
}