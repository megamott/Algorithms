package different_algorithms;

/**
 * @author Matvey Konoplyov
 */
public class AVLTree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addNode(new Node(5).getKey());
        tree.addNode(new Node(3).getKey());
        tree.addNode(new Node(4).getKey());
        tree.addNode(new Node(2).getKey());
        System.out.println(tree.toString());
    }
}

class Node {
    private Node left;
    private Node right;
    private Node parent;
    private int height = 1;
    private int key;

    public Node(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "height=" + height +
                ", key=" + key +
                '}';
    }
}

class Tree {
    private Node root;

    public void addNode(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            this.root = newNode;
            System.out.println("root " + root);
        } else {
            Node current = findVacation(key);
            if (current.getKey() - key > 0) {
                current.setLeft(newNode);
                current.getLeft().setParent(current);
                newHeight(current);
                System.out.println("parent " + current.getLeft().getParent() + " of " + current.getLeft());
            } else if (current.getKey() - key < 0) {
                current.setRight(newNode);
                current.getRight().setParent(current);
                newHeight(current);
                System.out.println("parent " + current.getRight().getParent() + " of " + current.getRight());
            }
        }
    }

    private int reHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(reHeight(node.getLeft()), reHeight(node.getRight()));
    }

    public void newHeight(Node node){
        do {
            node.setHeight(reHeight(node));
            node = node.getParent();
        } while (node != null);
    }

    public Node findVacation(int key) {
        Node current = root;
        Node result = null;
        int difference = 0;
        while (current != null) {
            difference = current.getKey() - key;
            result = current;
            if (difference > 0) {
                current = current.getLeft();
            } else if (difference < 0) {
                current = current.getRight();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}


























