package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
    TreeNode root;

    List<TreeNode> l;

    public Tree(TreeNode u) {
        root = u;
    }
    private void dfs(TreeNode u) {
        //if (u.sons.size() == 0) {l.add(u);return;}
        for (int i = 0; i < u.sons.size(); i++) {
            dfs(u.sons.get(i));
            u.totalWeight += u.sons.get(i).totalWeight;
        }
        l.add(u);
    }

    public List<TreeNode> postOrder() {
        l = new ArrayList<>();
        dfs(root);
        return l;
    }

    private void binaryDFS(TreeNode u) {
        int sum = 0;
        u.totalWeight = u.weight;
        if (u.sons.size() > 0) {
            binaryDFS(u.sons.get(0));
            sum += u.sons.get(0).totalWeight;
        }

        if (u.sibling != null) {
            binaryDFS(u.sibling);
            sum += u.sibling.totalWeight;
        }
        u.totalWeight += sum;
    }

    public void toBinary() {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode u = q.poll();
            List<TreeNode> s = u.sons;
            if (s.size() > 0) {
                q.add(s.get(0));
                TreeNode last = s.get(0);
                for (int i = 1; i < s.size(); i++) {
                    last.sibling = s.get(i);
                    last = s.get(i);
                    q.add(s.get(i));
                }
            }
        }
        binaryDFS(root);
    }
}
