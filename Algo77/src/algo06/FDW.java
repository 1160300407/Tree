package algo06;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Cell {
    TreeNode begin, end;
    int card;
    int rootweight;
    int[] next;
    List<TreeNode> nearlyopt;
    public Cell() {
        next = new int[2];
    }

    public void copy(Cell y) {
        begin = y.begin;
        end = y.end;
        card = y.card;
        rootweight = y.rootweight;
        next[0] = y.next[0];
        next[1] = y.next[1];
    }
}

class CellList {
    public Cell[] cl;

    public CellList(int len) {
        cl = new Cell[len];
    }
}

public class FDW {
    final int MAX_CARDINAL = 1000000;
    static int K;
    TreeNode root;
    CellList[] d;

    public FDW(TreeNode u, int _k) {
        K = _k;
        root = u;
        List<TreeNode> c = root.sons;

        int n = c.size();
        d = new CellList[K + 1];
        for (int i = 0; i <= K; i++)
            for (int j = 0; j <= n; j++)
                d[i] = new CellList(n + 1);
        for (int i = 0; i <= K; i++)
            for (int j = 0; j <= n; j++)
                d[i].cl[j] = new Cell();
        //[j] = new Cell();

        for (int s = root.weight; s <= K; s++) {
            d[s].cl[0].begin = root;
            d[s].cl[0].end = root;
            d[s].cl[0].card = 1;
            d[s].cl[0].rootweight = root.weight;
            d[s].cl[0].next[0] = 0;
            d[s].cl[0].next[1] = 0;
        }

        for (int j = 1; j <= n; j++) {
            for (int s = root.weight; s <= K; s++) {
                int si = s + c.get(j-1).weight;//c: 0..n-1
                Cell P = new Cell();
                if (si <= K && si >= 0)
                    P.copy(d[si].cl[j - 1]);
                else P.card = MAX_CARDINAL;
                int w = 0, m = 0;
                while (m < j && m < K && w < K) {
                    w += c.get(j - m - 1).weight;//c: 0..n-1
                    if (w <= K) {
                        int crd = d[s].cl[j - m - 1].card + 1;
                        int rw = d[s].cl[j - m - 1].rootweight;
                        if (crd < P.card || (crd == P.card && rw < P.rootweight)) {
                            P.begin = c.get(j - m - 1);
                            P.end = c.get(j - 1);
                            P.card = crd;
                            P.rootweight = rw;
                            P.next[0] = s;
                            P.next[1] = j - m - 1;
                        }
                    }
                    m++;
                }
                d[s].cl[j] = P;
            }
        }
    }

    public void getPartition() {
        List<Cell> result = new ArrayList<>();
        int i = root.weight, j = root.sons.size();
        while (i != 0 || j != 0) {
            result.add(d[i].cl[j]);
            System.out.println(d[i].cl[j].begin.label+"-"+d[i].cl[j].end.label+":"+d[i].cl[j].card+" rw:"+d[i].cl[j].rootweight);
            int tmp_i = d[i].cl[j].next[0];
            int tmp_j = d[i].cl[j].next[1];
            i = tmp_i;
            j = tmp_j;
        }
       // return result;
    }
}
