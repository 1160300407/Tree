package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
    static int wholeSize = 0;
    public int weight;
    public int totalWeight;
    public String label;
    public int index;
    public final List<TreeNode> sons = new ArrayList<>();
    public TreeNode sibling;
    public TreeNode(String name, int w) {
        label = name;
        weight = w;
        totalWeight = weight;
        wholeSize += weight;
    }

    public TreeNode(String name) {
        label = name;
        weight = (name.getBytes().length+7)/8 + 1; // another slots for metadata.
        totalWeight = weight;
        wholeSize += weight;
    }

    public void addSon(TreeNode... t) {
        Collections.addAll(sons, t);
    }

    public boolean isLeaf() {
        return sons.isEmpty();
    }

   // public int total
}
