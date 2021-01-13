package com.something;

import java.util.*;

public class RedBlackTree {
    public static void main(String[] args) {
        Tree.Node node = new Tree.Node(1);
        Tree tree = new Tree(node);

        Tree.Node node1 = new Tree.Node(2);
        tree.addNode(node1);

        Tree.Node node2 = new Tree.Node(3);
        tree.addNode(node2);

        Tree.Node node3 = new Tree.Node(4);
        tree.addNode(node3);

        Tree.Node node4 = new Tree.Node(5);
        tree.addNode(node4);

        Tree.Node node5 = new Tree.Node(6);
        tree.addNode(node5);

        System.out.println(tree.recursiveDeepGo(tree.getHead()));
        System.out.println(tree.deepGo(tree.getHead()));
    }

}

/**
 * Red-Black Tree class.
 * With recursive DFS and iterating DFS and BFS.
 */
class Tree {
    private Node head;

    /**
     * Root is always BLACK.
     *
     * @param head
     */
    public Tree(Node head) {
        this.head = head;
        head.setColor(true);
    }

    /**
     * Add a new node to {@link Tree}.
     *
     * @param node - new node.
     */
    public void addNode(Node node) {
        Node general = this.head;

        while (true) {
            if (general.getValue() > node.getValue()) {
                if (!general.hasSmallChild()) {
                    general.setSmallChild(node);
                    general.getSmallChild().setParent(general);
                    break;
                } else general = general.getSmallChild();
            } else if (general.getValue() < node.getValue()) {
                if (!general.hasBigChild()) {
                    general.setBigChild(node);
                    general.getBigChild().setParent(general);
                    break;
                } else general = general.getBigChild();
            }
        }

        balanceRedBlack(node);
    }

    /**
     * Find a node similar to adding method.
     *
     * @param node
     */
    public void findNode(Node node) {
        Node general = this.head;

        while (true) {
            if (general.getValue() > node.getValue()) {
                general = general.getSmallChild();
            } else if (general.getValue() < node.getValue()) {
                general = general.getBigChild();
            } else if (general.equals(node)) {
                System.out.println(general.getValue());
                break;
            }
        }
    }

    /**
     * This is the balancing method of Red Black Tree.
     * This method contains five general conditions of balancing this tree
     * and five method calls.
     * {@code case1} corresponds to the first rebalancing case when we have red parent and red uncle
     * of our checking node.
     * {@code case2right} and {@code case2left} corresponds to the second rebalancing case when we
     * have black uncle of our checking node and node is right/left (methods depends on it) child of RED parent.
     * Usually the second case is always followed by the third.
     * {@code case3left2right} and {@code case3right2left} corresponds to the third rebalancing case
     * when we have black uncle of our checking node and node is left/right (methods depends on it) child of RED parent.
     *
     * @param node checking node.
     */
    public void balanceRedBlack(Node node) {
        if (node == head) {
            node.setColor(true);
        } else if (node.getParent().hasParent()) {
            //Case 1
            if (node.getParent().getParent().hasBigChild() && node.getParent().getParent().hasSmallChild() &&
                    !node.getParent().isColor() &&
                    !node.getParent().getParent().getBigChild().isColor() &&
                    !node.getParent().getParent().getSmallChild().isColor() &&
                    (node == node.getParent().getSmallChild() || node == node.getParent().getBigChild())) {
                case1(node);
            }
            // Case 2 left
            else if (!node.getParent().isColor() &&
                    (!node.getParent().getParent().hasBigChild() || node.getParent().getParent().getBigChild().isColor()) &&
                    node == node.getParent().getBigChild()) {
                case2left(node);
            }
            // Case 2 right
            else if (!node.getParent().isColor() &&
                    (!node.getParent().getParent().hasSmallChild() || node.getParent().getParent().getSmallChild().isColor()) &&
                    node == node.getParent().getSmallChild()) {
                case2right(node);
            }
            // Case 3 left2right
            else if (!node.getParent().isColor() &&
                    (!node.getParent().getParent().hasBigChild() || node.getParent().getParent().getBigChild().isColor()) &&
                    node == node.getParent().getSmallChild()) {
                case3left2right(node);
            }
            // Case 3 right2left
            else if (!node.getParent().isColor() &&
                    (!node.getParent().getParent().hasSmallChild() || node.getParent().getParent().getSmallChild().isColor()) &&
                    node == node.getParent().getBigChild()) {
                case3right2left(node);
            }
        }


    }

    /**
     * Method calls when the rules of Red Black Tree are not followed:
     * Both children of each red node are black.
     *
     * @param node - new node in tree which calls rebalancing.
     */
    private void case1(Node node) {
        node.getParent().setColor(true);
        node.getParent().getParent().setColor(false);
        node.getParent().getParent().getBigChild().setColor(true);
        node.getParent().getParent().getSmallChild().setColor(true);
        balanceRedBlack(node.getParent().getParent());
    }

    /**
     * Method calls when the rules of Red Black Tree are not followed:
     * Both children of each red node are black.
     * After invocation this method allow {@code case3left2right} method from bottom node to end balancing.
     *
     * @param node - new node in tree which calls rebalancing.
     */
    private void case2left(Node node) {
        Node parent = node.parent;
        Node grandparent = node.parent.parent;

        if (node.hasSmallChild()) {
            parent.setBigChild(node.smallChild);
        } else parent.setBigChild(null);

        node.setSmallChild(parent);
        node.setParent(grandparent);
        grandparent.setSmallChild(node);
        parent.setParent(node);

        case3left2right(parent);
    }

    /**
     * Method calls when the rules of Red Black Tree are not followed:
     * Both children of each red node are black.
     * After invocation this method allow {@code case3right2left} method from bottom node to end balancing.
     *
     * @param node - new node in tree which calls rebalancing.
     */
    private void case2right(Node node) {
        Node parent = node.parent;
        Node grandparent = node.parent.parent;

        if (node.hasBigChild()) {
            parent.setSmallChild(node.smallChild);
        } else parent.setSmallChild(null);

        node.setBigChild(parent);
        node.setParent(grandparent);
        grandparent.setBigChild(node);
        parent.setParent(node);

        case3right2left(parent);
    }

    /**
     * Method calls when the rules of Red Black Tree are not followed:
     * Both children of each red node are black.
     *
     * @param node - new node in tree which calls rebalancing.
     */
    private void case3left2right(Node node) {
        Node parent = node.getParent();
        Node grandparent = node.getParent().getParent();

        if (parent.hasBigChild()) {
            grandparent.setSmallChild(parent.getBigChild());
        } else grandparent.setSmallChild(null);

        if (grandparent == head) {
            head = parent;
            parent.setParent(null);
        } else {
            parent.setParent(grandparent.getParent());
            grandparent.getParent().setSmallChild(parent);
        }
        parent.setBigChild(grandparent);

        grandparent.setParent(parent);
        grandparent.setColor(false);
        parent.setColor(true);

    }

    /**
     * Method calls when the rules of Red Black Tree are not followed:
     * Both children of each red node are black.
     *
     * @param node - new node in tree which calls rebalancing.
     */
    private void case3right2left(Node node) {
        Node parent = node.getParent();
        Node grandparent = node.getParent().getParent();

        if (parent.hasSmallChild()) {
            grandparent.setBigChild(parent.getSmallChild());
        } else grandparent.setBigChild(null);

        if (grandparent == head) {
            head = parent;
            parent.setParent(null);
        } else {
            parent.setParent(grandparent.getParent());
            grandparent.getParent().setBigChild(parent);
        }
        parent.setSmallChild(grandparent);

        grandparent.setParent(parent);
        grandparent.setColor(false);
        parent.setColor(true);
    }

    /**
     * Iterating DFS and BFS.
     * DFS - stack used.
     * BFS - deque used.
     * In this algorithm realised Stack with {@link Stack}
     *
     * @param head the algorithm starts from this node
     * @return sum of values of nodes.
     */
    public int deepGo(Node head) {
        int sum = 0;

        Stack stack = new Stack();

        stack.put(head);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.println(current.getValue());

            if (current.hasBigChild()) {
                stack.put(current.getBigChild());
            }
            if (current.hasSmallChild()) {
                stack.put(current.getSmallChild());
            }

            sum += current.getValue();

        }

        return sum;
    }

    /**
     * Recursive DFS.
     *
     * @param head the algorithm starts from this node
     * @return sum of values of nodes.
     */
    public int recursiveDeepGo(Node head) {
        int sum = head.getValue();

        if (head.hasBigChild()) {
            sum += recursiveDeepGo(head.getBigChild());
        }

        if (head.hasSmallChild()) {
            sum += recursiveDeepGo(head.getSmallChild());
        }

        return sum;
    }

    /**
     * To transform Stack class to Deque:
     * Rewrite put method with {@code pollLast} function.
     * <p>
     * To transform Deque class to Stack:
     * Rewrite put method with {@code pop} or {@code pollFirst} function of {@link LinkedList}.
     */
    static class Stack {

        private Deque<Node> list;

        public Stack() {
            this.list = new LinkedList<Node>();
        }

        public void put(Node node) {
            list.addFirst(node);
        }

        public Node pop() {
            return list.pollLast();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

    }

    /**
     * Node of Red Black Tree.
     */
    static class Node {
        private static final boolean RED = false;
        private static final boolean BLACK = true;

        private int value;
        private Node smallChild;
        private Node bigChild;
        private Node parent;
        private boolean color;

        /**
         * The new node is always RED.
         *
         * @param value - {@link Integer} value of new Node.
         */
        public Node(int value) {
            this.value = value;
            this.color = RED;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public boolean hasChild() {
            return smallChild != null || bigChild != null;
        }

        public boolean hasBigChild() {
            return bigChild != null;
        }

        public boolean hasSmallChild() {
            return smallChild != null;
        }

        public boolean hasParent() {
            return parent != null;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getSmallChild() {
            return smallChild;
        }

        public void setSmallChild(Node smallChild) {
            this.smallChild = smallChild;
        }

        public Node getBigChild() {
            return bigChild;
        }

        public void setBigChild(Node bigChild) {
            this.bigChild = bigChild;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}
