package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {
	// Divide and Conquer
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (root == null) {
            return res;
        }
        List<Integer> left = postorderTraversal(root.left);
        res.addAll(left);
        
        List<Integer> right = postorderTraversal(root.right);
        res.addAll(right);
        
        res.add(root.val);
        
        return res;
    }
        
    public List<Integer> postorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(res, root);
        return res;
    }
    
    void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        helper(res, root.left);
        helper(res, root.right);
        res.add(root.val);
    }
}
