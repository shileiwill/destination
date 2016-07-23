package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        helper(res, list, root, sum);
        
        return res;
    }
    
    public void helper(List<List<Integer>> res, List<Integer> list, TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        
        sum -= root.val;
        if (root.left == null && root.right == null) {
            if (sum == 0) { // Still need to backtrack, as there is left and then right
                list.add(root.val);
                res.add(new ArrayList<Integer>(list));
                list.remove(list.size() - 1);
            }
            return;
        }
        // Backtracking
        list.add(root.val);
        helper(res, list, root.left, sum);
        helper(res, list, root.right, sum);
        list.remove(list.size() - 1);
    }
    
}
