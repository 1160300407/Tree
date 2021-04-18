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
        tree.postOrder();//calc totalWeight
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();

        dfs(root);
        if (part.size() != 0) result.add(part);
    }

    public void dfs(TreeNode u) {
        int n = u.sons.size();
        for (int i = n - 1; i >= 0; i--) {
            if (u.totalWeight <= K) { // subtree's weight less than K, return
                if (partsum != 0) {
                    partsum = 0;
                    result.add(part);
                    part = new ArrayList<>();
                }
                //partsum = u.totalWeight;
                part.add(u);
                for (int j = 0; j <= i; j++)
                    part.add(u.sons.get(j));
                result.add(part);
                part = new ArrayList<>();
                u.totalWeight = 0;
                return;
            }
            if (partsum + u.sons.get(i).totalWeight <= K) {
                part.add(u.sons.get(i));
                partsum += u.sons.get(i).totalWeight;
                u.totalWeight -= u.sons.get(i).totalWeight;
            } else {
                if (partsum != 0) {
                    partsum = 0;
                    result.add(part);
                    part = new ArrayList<>();
                }
               // if (u.sons.get(i).totalWeight <= K) {
                    //partsum = u.sons.get(i).totalWeight;
                 //   part.add(u.sons.get(i));
                    u.totalWeight -= u.sons.get(i).totalWeight;
                //} else {
                //    u.totalWeight -= u.sons.get(i).totalWeight;
                    dfs(u.sons.get(i));
               // }
            }
        }
        if (u.totalWeight <= K) { // subtree's weight less than K, return
            if (partsum != 0) {
                partsum = 0;
                result.add(part);
                part = new ArrayList<>();
            }
            part.add(u);
            result.add(part);
            part = new ArrayList<>();
            u.totalWeight = 0;
            return;
        }
    }

    public String getPartition() {
        StringBuilder ans = new StringBuilder();
        ans.append(String.valueOf(result.size()));
        ans.append("\n");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++)
                ans.append(result.get(i).get(j).label +" ");
            ans.append("\n");
        }
        return ans.toString();
    }

    public int getPartitionNumber() {
        return result.size();
    }
}
