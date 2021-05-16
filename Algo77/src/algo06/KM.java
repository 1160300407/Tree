package algo06;

import util.Tree;
import util.TreeNode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KM {
    Tree tree;
    int K;
    List<List<TreeNode>> result;
    List<Integer> sum;
    List<TreeNode> part;
    int partsum = 0;
    TreeNode root;
    List<TreeNode> nodes;
    FileOutputStream out;
    public KM(TreeNode root, int k) {
        tree = new Tree(root);
        nodes = tree.postOrder();//calc totalWeight
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();
        sum = new ArrayList<>();
        this.root = root;
        sdfs();
        if (root.totalWeight != 0) {sum.add(root.totalWeight);result.add(part);}
    }

    public KM(TreeNode root, int k, FileOutputStream oo) {
        tree = new Tree(root);
        nodes = tree.postOrder();//calc totalWeight
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();
        sum = new ArrayList<>();
        out = oo;
        this.root = root;

        sdfs();
        if (root.totalWeight != 0) {sum.add(root.totalWeight);result.add(part);}
    }

    public Comparator<TreeNode> cmp = new Comparator<TreeNode>() {
        public int compare(TreeNode o1, TreeNode o2) {
            return o2.totalWeight - o1.totalWeight;
        }
    };


    public void sdfs() {
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode u = nodes.get(i);
            if (u.totalWeight <= K) continue;
            u.totalWeight = u.weight;
            PriorityQueue<TreeNode> q = new PriorityQueue<>(cmp);
            if (u.sons != null)
                for (int j = 0; j < u.sons.size(); j++) {
                    u.totalWeight += u.sons.get(j).totalWeight;
                    q.add(u.sons.get(j));
                }
            if (u.totalWeight <= K) continue;
            while (u.totalWeight > K && q.isEmpty() == false) {
                TreeNode t = q.poll();
                u.totalWeight -= t.totalWeight;
                sum.add(t.totalWeight);
                t.totalWeight = 0;
                part.add(u);
                result.add(part);
                part = new ArrayList<>();
            }
        }
    }

    public int getPartitionNumer() {
        return result.size();
    }
}
