package algo77;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Algo77 {
    int K;
    TreeNode root;
    public int ans;
    public Algo77(TreeNode _root, int _k) {
        K = _k;
        root = _root;
        ans = 0;
    }

    int partition(List<TreeNode> u,int start, int end) {
        int i = start, j = end;
        TreeNode base = u.get(i);
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
        List<TreeNode> result = new ArrayList<>();
        if (u.size() == 1) {
            if (u.get(0).totalWeight <= k)
                return null;
            result.add(u.get(0));
            return result;
        }

        int median = medianfind_and_halve(u);//s1 => u.sublist(median+1,size()-1)
        //List<TreeNode> s1 = u.subList(median+1, u.size());
        result.addAll(u.subList(median+1,u.size()));
        int sum = 0;
        for (TreeNode t : u.subList(0,median+1))
            sum += t.totalWeight;
        if (sum == k)
            return result;
        else if (sum < k) return split(result,k-sum);
        else {//sum > k
            List<TreeNode> ss = new ArrayList<>(u.subList(0, median + 1));
            result.addAll(split(ss, k));
            return result;
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
        //System.out.println(u.label + " sum = " + sum);
        if (sum > K - u.weight) {
            List<TreeNode> heavy = split(u.sons, K - u.weight);
            for (TreeNode t : heavy) {
                ans ++;
                //System.out.println("cut " + t.label);
                sum -= t.totalWeight;
            }
        }
        u.totalWeight = sum + u.weight;
    }

}
