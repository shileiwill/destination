package leetcode13.tree;

import chapter3.binaryTree.TreeNode;

/**
 * 285. Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorInBST {
	// From Bruce
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    	// If there is right node, very easy
        if (p.right != null) {
            TreeNode cur = p.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        
        // If no right node, the inorder successor is its parent or null
        TreeNode successor = null;
        while (root.val != p.val) {
            if (root.val < p.val) { // Go right
                // It is not successor in right, so dont change
                root = root.right;
            } else {
                successor = root; // This becomes a successor
                root = root.left;
            }
        }
        
        return successor;
    }
    // 不好理解！
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        }
    }
    
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        
        if (root.val >= p.val) {
            return inorderPredecessor(root.left, p);
        } else {
            TreeNode right = inorderPredecessor(root.right, p);
            return right != null ? right : root;
        }
    }
}
