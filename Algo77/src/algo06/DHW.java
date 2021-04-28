package algo06;

import util.Tree;
import util.TreeNode;

import java.util.*;

//optimal algorithm
public class DHW {
    final int MAX_CARDINAL = 1000000;
    static int K;
    Tree tree;
    public CellList[][] D;
    List<TreeNode> nodes;
    public int ans;
    TreeNode root;

    public int getTriW(TreeNode t) {
        Cell cm = D[t.index][t.weight].cl[t.sons.size()];
        int Dcm = cm.rootweight;
        int alt_s = t.weight+K-Dcm+1;
        int dtriw = 0;
        if (alt_s >= 0 && alt_s <= K) {
            Cell q = D[t.index][t.weight+K-Dcm+1].cl[t.sons.size()];
            if (q.card != MAX_CARDINAL)
                dtriw = Dcm - q.rootweight;
        }
        return dtriw;
    }

    public Comparator<TreeNode> cmp = new Comparator<TreeNode>() {
        public int compare(TreeNode o1, TreeNode o2) {
            return getTriW(o2) -getTriW(o1);
        }

    };

    public DHW(TreeNode root, int _k) {
        K = _k;
        this.root = root;
        tree = new Tree(root);
        nodes = tree.postOrder();
        D = new CellList[nodes.size()+1][K + 1];
        ans = 0;

        for (int v = 0; v < nodes.size(); v++) {
            TreeNode u = nodes.get(v);
            u.index = v;
            List<TreeNode> c = u.sons;
            int n = c.size();
            for (int i = 0; i <= K; i++)
                D[v][i] = new CellList(n+1);
            for (int i = 0; i <= K; i++)
                for (int j = 0; j <= n; j++)
                    D[v][i].cl[j] = new Cell();

            for (int s = u.weight; s <= K; s++) {
                D[v][s].cl[0].begin = u;
                D[v][s].cl[0].end = u;
                D[v][s].cl[0].card = 1;
                D[v][s].cl[0].rootweight = s;
                D[v][s].cl[0].next[0] = 0;
                D[v][s].cl[0].next[1] = 0;
                //D[v][s].cl[0].nearlyopt = new ArrayList<>();
            }

            for (int j = 1; j <= n; j++) {
                for (int s = u.weight; s <= K; s++) {
                    TreeNode now = c.get(j-1);
                    int Dcj = D[now.index][now.weight].cl[now.sons.size()].rootweight;
                    int si = s + Dcj;//D[v][c.get(j).weight].cl[n].rootweight;
                    Cell P = new Cell();
                    if (si <= K)
                        P.copy(D[v][si].cl[j - 1]);
                    else P.card = MAX_CARDINAL;
                    int w = 0, m = 0, dw = 0;
                    PriorityQueue<TreeNode> C = new PriorityQueue<>(cmp);
                    while (m < j && m < K && w - dw< K) {
                        TreeNode t = c.get(j-m-1);
                        w += D[t.index][t.weight].cl[t.sons.size()].rootweight;
                        dw += getTriW(t);
                        C.add(t);
                        if (w - dw <= K) {
                            PriorityQueue<TreeNode> Ct = new PriorityQueue<>(cmp);
                            Object[] nn= C.toArray();
                            for (Object nnt : nn) {
                                Ct.add((TreeNode) nnt);
                            }
                            int crd = D[v][s].cl[j - m - 1].card + 1;
                            int rw = D[v][s].cl[j - m - 1].rootweight;
                            int wi = w;
                            List<TreeNode> N = new ArrayList<>();
                            while (wi > K) {
                                TreeNode un = Ct.poll();
                                wi -= getTriW(un);
                                N.add(un);
                                crd ++;
                            }
                            if (crd < P.card || (crd == P.card && rw < P.rootweight)) {
                                P.begin = c.get(j - m-1);
                                P.end = c.get(j-1);
                                P.card = crd;
                                P.rootweight = rw;
                                P.next[0] = s;
                                P.next[1] = j - m - 1;
                                P.nearlyopt = N;
                            }
                        }
                        m++;
                    }
                    D[v][s].cl[j] = P;
                }
            }
        }
    }

    public void getPartition() {
        ans = 1;
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> qn = new LinkedList<>();
        q.add(root);
        qn.add(1);
        while (!q.isEmpty()) {
            TreeNode u = q.poll();
            Integer un = qn.poll();
            int v = u.index, i = u.weight, j = u.sons.size();

            Cell f;
            if (un.equals((Integer)1))
                f = D[v][i].cl[j];
            else {
                int Dcm = D[v][i].cl[j].rootweight;
                f = D[v][i - Dcm + K + 1].cl[j];
            }
            //System.out.println("u:"+u.index + " card:"+f.card + " nearlyopt:" + f.nearlyopt);
            ans += f.card-1;

            for (TreeNode t : u.sons) {
                if (f.nearlyopt == null || f.nearlyopt.contains(t) == false) {
                    q.add(t);
                    qn.add(1);
                }
            }

            while (i != 0 || j != 0) {
                if (D[v][i].cl[j].nearlyopt != null) {
                    for (TreeNode t : D[v][i].cl[j].nearlyopt) {
                        q.add(t);
                        qn.add(0);
                    }
                }
                //result.add(d[i][j]);
                int tmp_i = D[v][i].cl[j].next[0];
                int tmp_j = D[v][i].cl[j].next[1];
                i = tmp_i;
                j = tmp_j;
            }
        }

    }
    public int getAns() {return ans;}
}
