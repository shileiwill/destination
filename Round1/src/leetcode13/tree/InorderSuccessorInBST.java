package leetcode13.tree;

import chapter3.binaryTree.TreeNode;

/**
 * 285. Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
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
