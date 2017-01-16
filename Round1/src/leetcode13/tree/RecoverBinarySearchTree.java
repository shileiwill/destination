package leetcode13.tree;

import chapter3.binaryTree.TreeNode;

/**
 * 99. Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {
    TreeNode node1 = null, node2 = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        
        helper(root);
        
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
    
    void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        
        helper(root.left);
        
        if (node1 == null && root.val < prev.val) {
            node1 = prev;
        }// Can't use else here, e.g. [0, 1]
        if (node1 != null && root.val < prev.val) {
            node2 = root;
        }
        
        prev = root;
        helper(root.right);
    }
}
