import java.util.ArrayList;
import java.util.List;

public class Tree {
    TreeNode root;

    List<TreeNode> l;

    public Tree(TreeNode u) {
        root = u;
    }
    void dfs(TreeNode u) {
        //if (u.sons.size() == 0) {l.add(u);return;}
        if (u.isLeaf() == true) u.totalWeight = u.weight;
        else {
            u.totalWeight = u.weight;
            for (int i = 0; i < u.sons.size(); i++) {
                dfs(u.sons.get(i));
                u.totalWeight += u.sons.get(i).totalWeight;
            }
        }
        l.add(u);
    }

    public int count(TreeNode u) {
        if (u == null) return 0;
        int result = 1;
        for (int i = 0; i < u.sons.size(); i++) {
            result += count(u.sons.get(i));
            //u.totalWeight += u.sons.get(i).totalWeight;
        }
        return result;
    }

    public List<TreeNode> postOrder() {
        l = new ArrayList<>();
        dfs(root);
        return l;
    }

}
