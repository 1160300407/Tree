
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KM {
    Tree tree;
    int K;
    int partTotal = 0;
    int partExtra = 0;
    TreeNode root;
    List<TreeNode> nodes;
    FileOutputStream out;
    public KM(TreeNode root, int k){
        tree = new Tree(root);
        nodes = tree.postOrder();//calc totalWeight
        K = k;
        this.root = root;

        sdfs();
        if (root.totalWeight != 0) {
            partTotal ++;
            if (root.totalWeight + (root.lvirtual+1)*2 > K) partExtra ++;
            root.lvirtual = 0;
        }
    }

    /*public KM(TreeNode root, int k, FileOutputStream oo) throws IOException {
        tree = new Tree(root);
        nodes = tree.postOrder();//calc totalWeight
        K = k;
        out = oo;
        this.root = root;

        sdfs();
        if (root.totalWeight != 0) {
            partTotal ++;
        }
    }*/

    public Comparator<TreeNode> cmp = new Comparator<TreeNode>() {
        public int compare(TreeNode o1, TreeNode o2) {
            return o2.totalWeight - o1.totalWeight;
        }
    };

    public void sdfs(){
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode u = nodes.get(i);
            if (u.totalWeight <= K) continue;
            u.totalWeight = u.weight;
            u.lvirtual = 0;
            PriorityQueue<TreeNode> q = new PriorityQueue<>(cmp);
            if (u.sons != null)
            for (TreeNode v : u.sons) {
                u.totalWeight += v.totalWeight;
                u.lvirtual += v.lvirtual;
                q.add(v);
            }

            if (u.totalWeight <= K) continue;
            while (u.totalWeight > K && q.isEmpty() == false) {
                TreeNode t = q.poll();
                u.totalWeight -= t.totalWeight;//assert(t.tW <= K)

                partTotal ++;
                if (t.totalWeight + (t.lvirtual+1)*2 > K) {
                    partExtra ++;
                    //out.write((String.valueOf(partTotal)+" "+String.valueOf(t.totalWeight) +" "+String.valueOf(t.lvirtual+1)+"\n").getBytes());
                }
                t.totalWeight = 0;
                t.lvirtual = 0;
                u.lvirtual ++;
            }
        }
    }

    public int getPartitionNumer() {
        return partTotal;
    }
    public int getPartExtra() {return partExtra;}
}
