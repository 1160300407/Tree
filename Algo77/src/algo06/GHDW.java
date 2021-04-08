package algo06;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GHDW {
    static int K;
    TreeNode root;
    Cell[][][] d;
    List<TreeNode> nodes;

    public GHDW(List<TreeNode> nn, int _k, TreeNode u) {
        K = _k;
        root = u;
        nodes = nn;
        int n = u.sons.size();
        d = new Cell[nodes.size()][K + 1][n + 1];
        for (int v = 0; v <= nodes.size(); v++) {
            TreeNode vv = nodes.get(v);
            for (int i = 0; i <= K; i++)
                for (int j = 0; j <= n; j++)
                    d[v][i][j] = new Cell();

            for (int s = root.totalWeight; s <= K; s++) {
                d[v][s][0].begin = null;
                d[v][s][0].end = null;
                d[v][s][0].card = 0;
                d[v][s][0].rootweight = s;
                d[v][s][0].next[0] = 0;
                d[v][s][0].next[1] = 0;
            }

            List<TreeNode> c = root.sons;
            for (int j = 1; j <= vv.sons.size(); j++) {
                for (int s = root.weight; s <= K; s++) {
                    int si = s + c.get(j).weight;
                    Cell P = new Cell();
                    P.copy(d[si][j - 1]);

                    int w = 0, m = 0;
                    while (m < j && m < K && w < K) {
                        w += c.get(j - m).weight;
                        if (w <= K) {
                            int crd = d[s][j - m - 1].card + 1;
                            int rw = d[s][j - m - 1].rootweight;
                            if (crd < P.card && (crd == P.card && rw < P.rootweight)) {
                                P.begin = c.get(j - m);
                                P.end = c.get(j);
                                P.card = crd;
                                P.rootweight = rw;
                                P.next[0] = s;
                                P.next[1] = j - m - 1;
                            }
                        }
                        m++;
                    }
                    d[s][j] = P;
                }
            }
        }
    }

    public List<Cell> getPartition() {
        List<Cell> result = new ArrayList<>();
        int i = root.weight, j = root.sons.size();
        while (i != 0 || j != 0) {
            result.add(d[i][j]);
            int tmp_i = d[i][j].next[0];
            int tmp_j = d[i][j].next[1];
            i = tmp_i;
            j = tmp_j;
        }
        return result;
    }


}
