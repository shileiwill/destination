package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * 145. Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
 */
public class PostorderTraversal {
	// Important
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                res.addFirst(cur.val);
                cur = cur.right; // Go right!
            } else {
                TreeNode node = stack.pop(); // A small/lowest parent
                cur = node.left;
            }
        }
        
        return res;
    }
    
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
    
    public static void main(String[] args) {
		String s = "a\bcdabcdabc";
		System.out.println(s.lastIndexOf("bc"));
	}
}
