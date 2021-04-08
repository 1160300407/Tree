package util;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    TreeNode root;

    List<TreeNode> l;
    private void dfs(TreeNode u) {
        //if (u.sons.size() == 0) {l.add(u);return;}
        for (int i = 0; i < u.sons.size(); i++) {
            dfs(u.sons.get(i));
            u.totalWeight += u.sons.get(i).totalWeight;
        }
        l.add(u);
    }
    public List<TreeNode> postOrder() {
        l = new ArrayList<>();
        dfs(root);
        return l;
    }
}
