package leetcode7.binarysearch;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 230. Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
 */
public class KthSmallestElementInBST {
    int count = 0;
    int res = 0;
    public int kthSmallestRecursion(TreeNode root, int k) {
        if (root != null) {
            helper(root, k);
        }
        return res;
    }
    
    void helper(TreeNode node, int k) {
        if (node.left != null) {
            helper(node.left, k);
        } 
        if (++count == k) { // Visit current node
            res = node.val;
            return;
        }
        if (node.right != null) {
            helper(node.right, k);
        }
    }
    
    public int kthSmallestIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left; // Go to far left
            }
            
            TreeNode aNode = stack.pop();
            if (++count == k) {
                return aNode.val;
            }
            
            node = aNode.right;
        }
        
        return -1;
    }
    
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k - count - 1);
        }
        
        // k == count + 1
        return root.val;
    }
    
    int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}
