package algo06;

import util.Tree;
import util.TreeNode;

import java.util.List;

public class GHDW {
    final int MAX_CARDINAL = 1000000;
    static int K;
    Tree t;
    CellList[][] D;
    List<TreeNode> nodes;

//    public GHDW(int _k, TreeNode root) {
//        K = _k;
//        t = new Tree(root);
//        nodes = t.postOrder();
//        D = new CellList[nodes.size()][K + 1];
//
//        for (int v = 0; v < nodes.size(); v++) {
//            TreeNode u = nodes.get(v);
//            u.index = v;
//            List<TreeNode> c = u.sons;
//            int n = c.size();
//            for (int i = 0; i <= K; i++)
//                    D[v][i] = new CellList(n);
//            for (int i = 0; i <= K; i++)
//                for (int j = 0; j <= n; j++)
//                    D[v][i].cl[j] = new Cell();
//
//            for (int s = u.totalWeight; s <= K; s++) {
//                D[v][s].cl[0].begin = null;
//                D[v][s].cl[0].end = null;
//                D[v][s].cl[0].card = 0;
//                D[v][s].cl[0].rootweight = s;
//                D[v][s].cl[0].next[0] = 0;
//                D[v][s].cl[0].next[1] = 0;
//            }
//
//            for (int j = 1; j <= n; j++) {
//                for (int s = u.totalWeight; s <= K; s++) {
//                    int si = s + D[v][c.get(j).weight].cl[n].rootweight;
//                    Cell P = new Cell();
//                    if (si <= K)
//                        P.copy(D[v][si].cl[j - 1]);
//                    else P.card = MAX_CARDINAL;
//                    int w = 0, m = 0;
//                    while (m < j && m < K && w < K) {
//                        w += D[c.get(j-m).index][c.get(j - m).weight].cl[j-m].rootweight;
//                        if (w <= K) {
//                            int crd = D[v][s].cl[j - m - 1].card + 1;
//                            int rw = D[v][s].cl[j - m - 1].rootweight;
//                            if (crd < P.card && (crd == P.card && rw < P.rootweight)) {
//                                P.begin = c.get(j - m);
//                                P.end = c.get(j);
//                                P.card = crd;
//                                P.rootweight = rw;
//                                P.next[0] = s;
//                                P.next[1] = j - m - 1;
//                            }
//                        }
//                        m++;
//                    }
//                    d[s][j] = P;
//                }
//            }
//        }
//    }
//
//    public List<Cell> getPartition() {
//        List<Cell> result = new ArrayList<>();
//        int i = root.weight, j = root.sons.size();
//        while (i != 0 || j != 0) {
//            result.add(d[i][j]);
//            int tmp_i = d[i][j].next[0];
//            int tmp_j = d[i][j].next[1];
//            i = tmp_i;
//            j = tmp_j;
//        }
//        return result;
//    }


}
