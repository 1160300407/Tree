package algo77;

import util.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode a = new TreeNode("a",5);
        TreeNode b = new TreeNode("b",1);
        TreeNode c = new TreeNode("c",1);
        TreeNode d = new TreeNode("d",2);
        TreeNode e = new TreeNode("e",2);
        TreeNode f = new TreeNode("f",1);
        a.addSon(b,c,f);
        c.addSon(d,e);

        Algo77 algo = new Algo77(a, 5);
        algo.dfs(algo.root);
    }
}
