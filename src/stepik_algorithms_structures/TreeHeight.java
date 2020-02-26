package stepik_algorithms_structures;

import java.util.*;

/**
 * @author Matvey
 */
public class TreeHeight {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int vertexQuantity = scanner.nextInt();
//        Map<Integer, Integer> map = new TreeMap<>();
//        for (int i = 0; i < vertexQuantity; i++) {
//            map.put(i, scanner.nextInt());
//        }

        int count =0;

        int vertexQuantity = 5;
        Map<Integer, Integer> map = new HashMap<>();
        int[] tree = {4, -1, 4, 1, 1};
        for (int i = 0; i < vertexQuantity; i++) {
            map.put(i, tree[i]);
        }
        System.out.println(vertexQuantity + ", " + map);

        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            if (entry.getValue() == -1){count++;}
        }
    }

    class Tree {
        List<Tree> descendents = new ArrayList<>();
        int heightValue = descendents.size();

        public List<Tree> getDescendents() {
            return descendents;
        }

        public int height() {
            for (Tree node : descendents) {
                if (!node.getDescendents().isEmpty()) {
                    this.heightValue += node.height();
                }
            }
            return this.heightValue;
        }
    }
}

