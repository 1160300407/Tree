import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    static int total = 0;
    public int weight;
    public int totalWeight;
    public List<TreeNode> sons;
    public TreeNode(String name, int w) {
      //  label = name;
        weight = w;
        totalWeight = weight;
        total ++;
        if (total % 1000000 == 0) System.out.println(total+" nodes");
    }

    public TreeNode(String name, int w, int d) {
        weight = w;
        totalWeight = weight;
        total ++;
      //  index = total;
    }

    public TreeNode(String name) {
      //  label = name;
        weight = (name.getBytes().length+7)/8 + 1; // another slots for metadata.
        totalWeight = weight;
        total ++;
        if (total % 1000000 == 0) System.out.println(total+" nodes");
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

}
