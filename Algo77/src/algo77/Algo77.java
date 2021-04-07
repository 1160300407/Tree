package algo77;

import util.TreeNode;

import java.util.List;

public class Algo77 {
    int K;
    TreeNode root;
    Algo77(TreeNode _root, int _k) {
        K = _k;
        root = _root;
    }

    int partition(List<TreeNode> u,int start, int end) {
        int i = start, j = end;
        int x = (i+j)/2;
        TreeNode base = u.get(x);
        while (i<j) {
            while (i < j && u.get(j).totalWeight >= base.totalWeight) j--;
            if (i < j) {
                u.set(i, u.get(j));
                i++;
            }
            while (i < j && u.get(i).totalWeight <= base.totalWeight) i++;
            if (i < j) {
                u.set(j, u.get(i));
                j--;
            }
        }
        u.set(i, base);
        return i;
    }

    int medianfind_and_halve(List<TreeNode> u) {
        int start = 0, end = u.size()-1;
        int mid = end/2;
        int index = partition(u, start, end);
        while (index != mid) {
            if (index < mid) {
                start = index + 1;
            } else {
                end = index - 1;
            }
            index = partition(u, start, end);
        }
        return mid;
    }

    List<TreeNode> split(List<TreeNode> u, int k) {
        if (u.size() == 1) {
            if (u.get(0).totalWeight <= k)
                return null;
            else return u;
        }

        int median = medianfind_and_halve(u);//s1 => u.sublist(median+1,size()-1)
        List<TreeNode> s1 = u.subList(median+1, u.size()-1);
        int sum = 0;
        for (TreeNode t : u.subList(0, median))
            sum += t.totalWeight;
        if (sum == k)
            return s1;
        else if (sum < k) return split(s1,k-sum);
        else {//sum > k
            List<TreeNode> ss = split(u.subList(0, median), k);
            ss.addAll(s1);
            return ss;
        }
    }

    public void dfs(TreeNode u) {
        if (u.isLeaf()) {
            u.totalWeight = u.weight;
            return;
        }
        int sum = 0;
        for (TreeNode p : u.sons) {
            dfs(p);
            sum += p.totalWeight;
        }

        if (sum > K - u.weight) {
            List<TreeNode> heavy = split(u.sons, K - u.weight);
            for (TreeNode t : heavy) {
                System.out.println("cut " + t.label);
                sum -= t.totalWeight;
            }
        }

        u.totalWeight = sum + u.weight;
    }

}
