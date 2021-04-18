package algo06;

import util.Tree;
import util.TreeNode;

import java.util.*;

public class KM {
    Tree tree;
    int K;
    List<List<TreeNode>> result;
    List<TreeNode> part;
    int partsum = 0;

    public KM(TreeNode root, int k) {
        tree = new Tree(root);
        tree.postOrder();//calc totalWeight
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();

        dfs(root);
        if (part.size() != 0) result.add(part);
    }

    public Comparator<TreeNode> cmp = new Comparator<TreeNode>() {
        public int compare(TreeNode o1, TreeNode o2) {
            return o2.totalWeight - o1.totalWeight;
        }
    };

    public void dfs(TreeNode u) {
        int n = u.sons.size();
        if (u.totalWeight <= K) {
            part.add(u);
            for (int i = 0; i < n; i++)
                if (u.sons.get(i).totalWeight != 0) part.add(u.sons.get(i));
            result.add(part);
            partsum = 0;
            part = new ArrayList<>();
        } else {
            PriorityQueue<TreeNode> q = new PriorityQueue<>(cmp);
            for (TreeNode t : u.sons) q.add(t);
            while (!q.isEmpty() && u.totalWeight > K) {
                TreeNode t = q.poll();
                dfs(t);
                u.totalWeight -= t.totalWeight;
            }
            part.add(u);
            while (!q.isEmpty()) {
                TreeNode t = q.poll();
                part.add(t);
            }
            result.add(part);
            partsum = 0;
            part = new ArrayList<>();
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

    public int getPartitionNumer() {
        return result.size();
    }
}
