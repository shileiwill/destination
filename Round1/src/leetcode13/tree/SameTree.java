package leetcode13.tree;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 100. Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class SameTree {
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        
        if (p.val != q.val) {
            return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        
        if (p.val != q.val) {
            return false;
        }
        
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(p);
        s2.push(q);
        
        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();
            
            if (n1.val != n2.val) {
                return false;
            }
            
            if (n1.left != null && n2.left != null) {
                s1.push(n1.left);
                s2.push(n2.left);
            } else if (n1.left == null && n2.left == null) {
                // Do nothing, but this is fine
            } else { // Only 1 is null
                return false;
            }
            
            if (n1.right != null && n2.right != null) {
                s1.push(n1.right);
                s2.push(n2.right);
            } else if (n1.right == null && n2.right == null) {
                // Do nothing, but this is fine
            } else { // Only 1 is null
                return false;
            }
        }
        
        return s1.isEmpty() && s2.isEmpty();
    }
}
