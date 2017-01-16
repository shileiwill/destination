package leetcode13.tree;

import chapter3.binaryTree.TreeNode;

/**
 * 250. Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
 */
public class CountUnivalueSubtrees {
    int count = 0;
    public int countUnivalSubtrees2(TreeNode root) {
        helper(root);
        return count;
    }
    
    void helper(TreeNode node) {
        if (node == null) return;

        helper(node.left);
        if (isUnivalSubtree(node)) {
            count++;
        }
        helper(node.right);
    }
    
    boolean isUnivalSubtree (TreeNode node) {
        if (node.left == null && node.right == null) {
            return true;// itself
        } else if (node.left != null && node.right != null) {
            return (node.val == node.left.val && node.val == node.right.val && isUnivalSubtree(node.left) && isUnivalSubtree(node.right));
        } else if (node.left != null) {
            return (node.val == node.left.val && isUnivalSubtree(node.left));
        } else if (node.right != null) {
            return (node.val == node.right.val && isUnivalSubtree(node.right));
        }
        return false; // Will never come here
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        int[] res = new int[1];
        helper2(root, res);
        return res[0];
    }
    
    boolean helper2(TreeNode node, int[] res) {
        if (node == null) {
            return true;
        }
        boolean left = helper2(node.left, res);
        boolean right = helper2(node.right, res);
        
        if (left && right) {
            if (node.left != null && node.left.val != node.val) {
                return false;
            }
            if (node.right != null && node.right.val != node.val) {
                return false;
            }
            res[0]++;
            return true;
        }
        
        return false;
    }
}
