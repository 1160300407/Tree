package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode {
    public final int weight;
    public int totalWeight;
    public String label;
    public final List<TreeNode> sons = new ArrayList<>();

    public TreeNode(String name, int w) {
        label = name;
        weight = w;
    }

    public void addSon(TreeNode... t) {
        Collections.addAll(sons, t);
    }

    public boolean isLeaf() {
        return sons.isEmpty();
    }

}
