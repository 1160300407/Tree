package algo06;

import util.Tree;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

    class Cell {
        TreeNode begin,end;
        int card;
        int rootweight;
        int[] next;
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
        Cell[] cellList;
        public CellList(int len) {
            cellList = new Cell[len];
        }
    }

public class FDW {
    static int K;
    TreeNode root;
    CellList[] d;
    public FDW(int _k, TreeNode u) {
        K = _k;
        root = u;
        int n  = u.sons.size();
        d = new CellList[K+1];
        for (int i = 0; i <= K; i++)
            for (int j = 0; j <= n; j++)
                d[i] = new CellList(n+1);
        for (int i = 0; i <= K; i++)
            for (int j = 0; j <= n; j++)
                d[i].cellList[j] = new Cell();
            //[j] = new Cell();

        for (int s = root.weight; s <= K; s++) {
            d[s].cellList[0].begin = root;
            d[s].[0].end = root;
            d[s][0].card = 1;
            d[s][0].rootweight = root.weight;
            d[s][0].next[0] = 0;
            d[s][0].next[1] = 0;
        }

        List<TreeNode> c = root.sons;
        for (int j = 1; j <= n; j++) {
            for (int s = root.weight; s <= K; s++) {
                int si = s + c.get(j).weight;
                Cell P = new Cell();
                P.copy(d[si][j-1]);

                int w = 0, m = 0;
                while (m < j && m < K && w < K) {



              if (w <= K) {
                        in



                        t crd = d[s][j-m-1].card + 1;



                        int rw = d[s][j-m-1].rootweight;
                        if (crd < P.card && (crd == P.card && rw < P.rootweight)) {
                            P.begin = c.get(j-m);
                            P.end = c.get(j);
                            P.card = crd;
                            P.rootweight = rw;
                            P.next[0] = s;
                            P.next[1] = j-m-1;
                        }
                    }
                    m ++;
                }
                d[s][j] = P;
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
