package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (root == null) {
            return res;
        }
        List<Integer> left = inorderTraversal(root.left);
        res.addAll(left);
        
        res.add(root.val);
        List<Integer> right = inorderTraversal(root.right);
        res.addAll(right);
        
        
        return res;
    }
        
    public List<Integer> inorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(res, root);
        return res;
    }
    
    void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }
}
