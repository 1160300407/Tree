package algo06;

import util.Tree;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RS {
    Tree tree;
    int K;
    List<List<TreeNode>> result;
    List<TreeNode> part;
    int partsum = 0;

    public RS(TreeNode root, int k) {
        tree = new Tree(root);
        tree.postOrder();
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();

        dfs(null,root);
        if (part.size() != 0) result.add(part);
    }

    public void dfs(TreeNode u) {
        if (partsum + u.weight <= K && (father == null || father.index == result.size())) {//connected
            u.index = result.size();
            part.add(u);
            partsum += u.weight;
            for (int i = 0; i < u.sons.size(); i++)
                dfs(u, u.sons.get(i));
        } else {//either current part is full or u isn't connected with <part>
            // (assert u is connected with <part> iff u's father is part(0))
            result.add(part);
            part = new ArrayList<>();
            partsum = 0;
        }
    }
}
}
