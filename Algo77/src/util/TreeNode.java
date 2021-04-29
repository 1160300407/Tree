package util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    static int wholeSize = 0;
    static int total = 0;
    public int weight;
    public int totalWeight;
    //public String label;
    public int index;
    public List<TreeNode> sons;
    public TreeNode sibling;
    public TreeNode(String name, int w) {
      //  label = name;
        weight = w;
        totalWeight = weight;
        wholeSize += weight;
        total ++;
        if (total % 100000 == 0) System.out.println(total+" nodes");
        if (total == 5000000) {
            System.out.println("pause");
        }
    }

    public TreeNode(String name) {
      //  label = name;
        weight = (name.getBytes().length+7)/8 + 1; // another slots for metadata.
        totalWeight = weight;
        wholeSize += weight;
        total ++;
        if (total % 100000 == 0) System.out.println(total+" nodes");
        if (total == 5000000) {
            System.out.println("pause");
        }
    }

    public void addSon(TreeNode... t) {
        if (sons == null) {
            sons = new LinkedList<>();
            Collections.addAll(sons, t);
        } else Collections.addAll(sons, t);
    }

    public void addSon(TreeNode t) {
        if (sons == null) {
            sons = new LinkedList<>();
            sons.add(t);
        } else sons.add(t);
    }

    public boolean isLeaf() {
        return (sons == null || sons.isEmpty());
    }

   // public int total
}
