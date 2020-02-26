package stepik_algorithms_structures;

import java.util.*;

/**
 * @author Matvey
 */
public class TreeHeight {
    public static void main(String[] args) {
        int vertexQuantity = 5;
        Map<Integer, Integer> map = new HashMap<>();
        int[] tree = {4, -1, 4, 1, 1};
        for (int i = 0; i < vertexQuantity; i++) {
            map.put(i, tree[i]);
        }
        System.out.println(vertexQuantity + ", " + map);
        Graph graph = new Graph();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()
        ) {
            graph.addNode(new Node(entry.getKey(), entry.getValue()));
        }

        graph.SetContacts();

        System.out.println(graph);
    }
}

class Graph {
    private Node root;
    private List<Node> tree = new LinkedList<>();

    void addNode(Node node) {
        if (node.getParentKey() == -1) {
            this.root = node;
            this.tree.add(node);
        } else {
            this.tree.add(node);
        }
    }

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

    void SetContacts() {
        for (Node node : tree
        ) {
            if (node != root) {
                node.setParent(getParentByKey(node.getParentKey()));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node n : tree) {
            result.append("Узел с ключом " + n.getKey());

            if (n.getParent() != null) {
                result.append(", родитель - узел с ключом " + n.getParent().getKey());
            } else {
                result.append(", корневой узел");
            }

            if (!n.getChildren().isEmpty()) {
                result.append(", потомки - ");
                for (Node ch : n.getChildren()) {
                    result.append(ch.getKey() + ", ");
                }
                result.append("\n");
            } else {
                result.append(", потомков нет" + "\n");
            }
        }
        return result.toString();
    }
}

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






