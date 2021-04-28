package algo06;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * only father-sons relation concerned.
 */
public class PUREDFS {
    TreeNode root;
    int K;
    List<List<TreeNode>> result;
    List<TreeNode> part;
    int partsum = 0;
    public PUREDFS(TreeNode u, int k) {
        root = u;
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();

        dfs(null, root);
        if (part.size() != 0) result.add(part);
    }
    public void dfs(TreeNode father, TreeNode u) {
        if (partsum + u.weight <= K && (father == null || father.index == result.size())) {//connected
            u.index = result.size();
            part.add(u);
            partsum += u.weight;
        } else {//either current part is full or u isn't connected with <part>
                // (assert u is connected with <part> iff u's father is part(0))
            result.add(part);
            part = new ArrayList<>();
            part.add(u);
            u.index = result.size();
            partsum = u.weight;
        }
        for (int i = 0; i < u.sons.size(); i++)
            dfs(u, u.sons.get(i));
    }

    public String getPartition() {
        StringBuilder ans = new StringBuilder();
        ans.append(String.valueOf(result.size()));
        ans.append("\n");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++)
               // ans.append(result.get(i).get(j).label +" ");
            ans.append("\n");
        }
        return ans.toString();
    }

    public int getPartitionNumber() {
        return result.size();
    }
}
