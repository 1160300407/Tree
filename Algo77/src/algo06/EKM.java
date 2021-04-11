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
        if (u.totalWeight <= K) {
            bfsAddPart(u);
            result.add(part);
            partsum = 0;
            part = new ArrayList<>();
            u.totalWeight = 0;
        } else {//u.totalWeight > K, no way that both paths is null.
            int sonsWeight = 0;
            if (u.sons.size() > 0) sonsWeight = u.sons.get(0).totalWeight;
            int sibWeight = 0;
            if (u.sibling.totalWeight > 0) sibWeight = u.sibling.totalWeight;

            if (sonsWeight > sibWeight) {
                dfs(u.sons.get(0));
            } else {
                dfs(u.sibling);
            }

            if (u.totalWeight )

        }


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


}
