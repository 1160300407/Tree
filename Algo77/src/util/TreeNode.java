package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
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
    }

    public TreeNode(String name) {
        label = name;
        weight = name.length() + 1; // another slots for metadata.
        totalWeight = weight;
    }

    public void addSon(TreeNode... t) {
        Collections.addAll(sons, t);
    }

    public boolean isLeaf() {
        return sons.isEmpty();
    }

}
