package algo06;

import util.Tree;
import util.TreeNode;

import java.util.*;

public class KM {
    Tree tree;
    int K;
    List<List<TreeNode>> result;
    List<Integer> sum;
    List<TreeNode> part;
    int partsum = 0;
    TreeNode root;
    List<TreeNode> nodes;

    public KM(TreeNode root, int k) {
        tree = new Tree(root);
        nodes = tree.postOrder();//calc totalWeight
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();
        sum = new ArrayList<>();
        this.root = root;

        dfs(root);
        if (root.totalWeight != 0) {sum.add(root.totalWeight);result.add(part);}
    }

    public Comparator<TreeNode> cmp = new Comparator<TreeNode>() {
        public int compare(TreeNode o1, TreeNode o2) {
            return o2.totalWeight - o1.totalWeight;
        }
    };

    public void dfs() {
        for (int i = 0; i < nodes.size(); i++) {

            TreeNode u = nodes.get(i);//System.out.println(u.totalWeight);
            if (u.totalWeight <= K) continue;
            u.totalWeight = u.weight;
            PriorityQueue<TreeNode> q = new PriorityQueue<>(cmp);
            for (int j = 0; j < u.sons.size(); j++) {
                u.totalWeight += u.sons.get(j).totalWeight;
                q.add(u.sons.get(j));
            }
            if (u.totalWeight <= K) continue;
            pruneChildren(u, q);
        }
    }

    private void pruneChildren(TreeNode u) {
        while (u.totalWeight > K) {
            ///int n = u.sons.size();
            TreeNode v = null;
            for (TreeNode t : u.sons)
                if (v == null || t.totalWeight > v.totalWeight) {
                    v = t;
                }
            if (partsum + v.totalWeight <= K) {
                part.add(v);
                partsum += v.totalWeight;
                u.totalWeight -= v.totalWeight;
                v.totalWeight = 0;
            } else {
                result.add(part);
                sum.add(partsum);
                partsum = 0;
                part = new ArrayList<>();
            }
        }
    }

    private void pruneChildren(TreeNode u, PriorityQueue<TreeNode> q) {
        while (!q.isEmpty() && u.totalWeight > K) {
            TreeNode t = q.peek();
            if (partsum + t.totalWeight <= K) {
                part.add(t);
                partsum += t.totalWeight;
                u.totalWeight -= t.totalWeight;
                q.poll();
                t.totalWeight = 0;
            } else {
                result.add(part);
                sum.add(partsum);
                partsum = 0;
                part = new ArrayList<>();
            }
        }
        if (partsum != 0) {//q isEmpty,
            result.add(part);
            sum.add(partsum);
            partsum = 0;
            part = new ArrayList<>();
        }
    }

    public void dfs(TreeNode u) {
        if (u.totalWeight <= K) return;
        //u.totalWeight > K
        PriorityQueue<TreeNode> q = new PriorityQueue<>(cmp);
        u.totalWeight = u.weight;
        for (TreeNode t : u.sons) {
            if (t.totalWeight > K)
                dfs(t);
            u.totalWeight += t.totalWeight;
            q.add(t);
        }

        //
        if (u.totalWeight <= K) return;

        //int w = 0;
        pruneChildren(u, q);

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
        int tot = 0;
        for (int i = 0; i < sum.size(); i++)
        {
            tot += sum.get(i);
           // System.out.println(sum.get(i));
            if (sum.get(i) > 352) System.out.println("---------------");
        }
        System.out.println(tot);
        return result.size();
    }
}
