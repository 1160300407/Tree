package algo77;

import algo06.FDW;
import util.TreeNode;

public class Main {

    public static void main(String[] args) {
//        TreeNode a = new TreeNode("a",5);
//        TreeNode b = new TreeNode("b",1);
//        TreeNode c = new TreeNode("c",1);
//        TreeNode d = new TreeNode("d",2);
//        TreeNode e = new TreeNode("e",2);
//        TreeNode f = new TreeNode("f",1);
//        a.addSon(b,c,f);
//        c.addSon(d,e);
        TreeNode a = new TreeNode("a",3);
        TreeNode b = new TreeNode("b",2);
        TreeNode c = new TreeNode("c",1);
        TreeNode d = new TreeNode("d",2);
        TreeNode e = new TreeNode("e",2);
        TreeNode f = new TreeNode("f",1);
        TreeNode g = new TreeNode("g",1);
        TreeNode h = new TreeNode("h",2);
        a.addSon(b,c,f,g,h);
        c.addSon(d,e);
        FDW fd = new FDW(a, 5);
        fd.getPartition();
       // List<Cell> r = fd.getPartition();
        //Algo77 algo = new Algo77(a, 5);
        //algo.dfs(algo.root);
    }

}
