/*
Формата ввода.
Первая строка содержит натуральное число n.
Вторая строка содержит n целых неотрицательных чисел parent[0]...parentn[n-1].
Для каждого 0 <= i <= n-1, parent[i] — родитель вершины i;
если parent[i] = -1, то i является корнем.
Гарантируется, что корень ровно один. Гарантируется, что данная последовательность задает дерево.
Формат вывода.
Высота дерева.
Sample Input:
10
9 7 5 5 2 9 9 9 2 -1
Sample Output:
4
Sample Input:
5
4 -1 4 1 1
Sample Output:
3
Sample Input:
5
-1 0 4 0 3
Sample Output:
4
*/
package stepik_algorithms_structures;

import java.util.*;

/**
 * @author Matvey
 */
public class TreeHeight {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        int vertexQuantity = 10;
        Map<Integer, Integer> map = new HashMap<>();
        int[] tree = {9, 7, 5, 5, 2, 9, 9, 9, 2, -1};
        for (int i = 0; i < vertexQuantity; i++) {
            map.put(i, tree[i]);
        }
        System.out.println(vertexQuantity + ", " + map);
        Graph graph = new Graph();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()
        ) {
            graph.addNode(new Node(entry.getKey(), entry.getValue()));
        }
        //Установление связей
        graph.SetContacts();
        //Проверка
        System.out.println(graph);
        System.out.println(graph.getHeight(graph.getRoot()));
    }
}

/**
 * Класс, задающий дерево
 */
class Graph {
    private Node root;
    private List<Node> tree = new LinkedList<>();

    /**
     * Добавление узла в дерево
     * @param node
     */
    void addNode(Node node) {
        if (node.getParentKey() == -1) {
            this.root = node;
            this.tree.add(node);
        } else {
            this.tree.add(node);
        }
    }

    /**
     * Получение корня дерева
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Получение родителя по ключу родителя
     * @param parentKey
     * @return
     */
    private Node getParentByKey(int parentKey) {
        Node parentNode = null;
        for (Node node : tree
        ) {
            if (node.getKey() == parentKey) {
                parentNode = node;
            }
        }
        return parentNode;
    }

    /**
     * Связывание узла с родителем
     * Опрделение детей узла
     * Установление связей между узлами
     */
    void SetContacts() {
        for (Node node : tree
        ) {
            if (node != root) {
                node.setParent(getParentByKey(node.getParentKey()));
                node.getParent().getChildren().add(node);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (Node node : tree
        ) {
            outputString.append("Node " + node.getKey());
            if (node.getParent() != null) {
                outputString.append(" with parent: " + node.getParentKey());
            } else {
                outputString.append(" is root");
            }
            if (!node.getChildren().isEmpty()) {
                outputString.append(" and has children: ");
                for (Node child : node.getChildren()
                ) {
                    outputString.append(child.getKey());
                    outputString.append(" ");
                }
            } else {
                outputString.append(" hasn't got children");
            }
            outputString.append("\n");
        }
        return outputString.toString();
    }

    /**
     * Получение высоты дерева рекурсивным методом
     * @param node
     * @return
     */
    public int getHeight(Node node) {
        if (node == null) return 0;
        List<Integer> heights = new LinkedList<>();
        int childrenCount = node.getChildren().size();
        for (int i = 0; i < childrenCount; i++) {
            heights.add(getHeight(node.getChildren().get(i)));
        }
        if (!heights.isEmpty()) {
            heights.add(1 + Collections.max(heights));
        } else return 1;
        return Collections.max(heights);
    }
}

/**
 * Класс, задающий узел
 */
class Node {
    private int key, parentKey;
    private Node parent;
    private List<Node> children;

    Node(int key, int parentKey) {
        this.parentKey = parentKey;
        this.key = key;
        this.children = new LinkedList<>();
    }

    void setParent(Node parent) {
        this.parent = parent;
    }

    List<Node> getChildren() {
        return children;
    }

    int getKey() {
        return key;
    }

    int getParentKey() {
        return parentKey;
    }

    Node getParent() {
        return parent;
    }
}






