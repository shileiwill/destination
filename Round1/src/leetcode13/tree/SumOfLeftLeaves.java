package leetcode13.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 404. Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {
    int sum = 0;
    public int sumOfLeftLeavesDFSRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, false);
        return sum;
    }
    
    void helper(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && isLeft) { // It's a leaf and it is left of parent
            sum += node.val;
            return;
        }
        helper(node.left, true);
        helper(node.right, false);
    }
    
    public int sumOfLeftLeavesBFSIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            // 1 level above
            //if (node.left == null && node.right == null && ) {
            if (node.left != null && node.left.right == null && node.left.left == null) {
                sum += node.left.val;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return sum;
    }
    
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) { // If it is a leaf
                    sum += node.left.val;
                } 
                // else { // Doesn't matter
                    stack.push(node.left);
                // }
            }
            if (node.right != null) { // Push to stack only if the right node has children, basically not a leaf
                // if (node.right.left != null || node.right.right != null) { // Doesn't matter
                    stack.push(node.right);
                // }
            }
        }
        
        return sum;
    }
}
