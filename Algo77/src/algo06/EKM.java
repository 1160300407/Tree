package algo06;

import util.Tree;
import util.TreeNode;

import java.util.*;

public class EKM {
    Tree tree;
    int K;
    List<List<TreeNode>> result;
    List<TreeNode> part;
    int partsum = 0;

    public EKM(TreeNode root, int k) {
        tree = new Tree(root);
        tree.toBinary();//to binary tree and calc totalWeight
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

    private void bfsAddPart(TreeNode u) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(u);
        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            t.totalWeight = 0;
            part.add(t);

            if (t.sons.size() > 0 && t.sons.get(0).totalWeight != 0) {
                q.add(t.sons.get(0));
            }
            if (t.sibling != null && t.sibling.totalWeight != 0) {
                q.add(t.sibling);
            }
        }
    }

    public void dfs(TreeNode u) {
        int n = u.sons.size();
        u.totalWeight = u.weight;
        if (u.sibling != null) u.totalWeight += u.sibling.totalWeight;
        if (u.sons.size() > 0) u.totalWeight += u.sons.get(0).totalWeight;

        if (partsum + u.totalWeight <= K) {
            partsum += u.totalWeight;
            bfsAddPart(u);
            return;
        } else if (partsum != 0) {//partsum + u.totalWeight > K, make partsum a new part.
            result.add(part);
            partsum = 0;
            part = new ArrayList<>();
        } else {//u.totalWeight > K, no way that both paths is null.
            do {
                int sonsWeight = 0;
                if (u.sons.size() > 0) sonsWeight = u.sons.get(0).totalWeight;
                int sibWeight = 0;
                if (u.sibling != null) sibWeight = u.sibling.totalWeight;

                u.totalWeight -= Integer.max(sonsWeight, sibWeight);//update u.totalWeight
                if (sonsWeight > sibWeight) {
                    dfs(u.sons.get(0));
                    u.totalWeight += u.sons.get(0).totalWeight;
                } else {
                    dfs(u.sibling);
                    u.totalWeight += u.sibling.totalWeight;
                }
            } while (u.totalWeight <= K);

            if (u.totalWeight <= K) {
                part = new ArrayList<>();
                bfsAddPart(u);
                partsum = u.totalWeight;
            }
        }

    }

    public String getPartition() {
        StringBuilder ans = new StringBuilder();
        ans.append(String.valueOf(result.size()));
        ans.append("\n");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++)
                ans.append(result.get(i).get(j).label + " ");
            ans.append("\n");
        }
        return ans.toString();
    }


}
