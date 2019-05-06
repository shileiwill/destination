package company.snapchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].
 * @author Lei
 *
 */
public class BinaryTreePreoderTraversal {
	// One difference between Divide & Conquer and Recursion is, Divide & Conquer will not pass result as parameter, but as return
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        // Divide
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);
        
        // Conquer
        res.add(root.val);
        res.addAll(left);
        res.addAll(right);
        
        return res;
    }
    public List<Integer> preorderTraversalIteration(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if (root == null) {
            return res;
        }
        
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            
            // Stack is first in last out, so put right first to make sure it goes later
            if (node.right != null) {
                stack.push(node.right);
            }
            
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        return res;
    }
    
    public List<Integer> preorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(res, root);
        return res;
    }
    
    private void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        helper(res, root.left);
        helper(res, root.right);
    }
}
