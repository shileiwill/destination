package chapter3.binaryTree.BST;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;
/**
 * 98. Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
 * @author Lei
 *
 */
public class ValidBinarySearchTree {
	
    class ResultType {
        boolean isBST;
        long max;
        long min;
        
        ResultType(boolean isBST, long max, long min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
    
    public boolean isValidBST2(TreeNode root) {
        ResultType rt = helper(root);
        return rt.isBST;
    }
    
    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        // Conquer
        if (!left.isBST || !right.isBST) { // isBST is most important
            return new ResultType(false, -1, -22); // Can be anything for min and max
        }
        if (left.max >= root.val || right.min <= root.val) {
            return new ResultType(false, 2, 33); // Can be anything for min and max
        }
        
        return new ResultType(true, Math.max(root.val, right.max), Math.min(root.val, left.min));
    }
    
	// This way, we need to inOrder traversal, and then compare results, which is not efficient.
    public boolean isValidBST(TreeNode root) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        
        //inOrderTraversal(res, root);
        res = inOrderTraversalDC(root);
        
        for (int i = 0; i < res.size() - 1; i++) {
            TreeNode left = res.get(i);
            TreeNode right = res.get(i + 1);
            if (left.val >= right.val) {
                return false;
            }
        }
        
        return true;
    }
    
    List<TreeNode> inOrderTraversalDC(TreeNode root) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (root == null) {
            return res;
        }
        
        List<TreeNode> left = inOrderTraversalDC(root.left);
        res.addAll(left);
        
        res.add(root);
        
        List<TreeNode> right = inOrderTraversalDC(root.right);
        res.addAll(right);
        
        return res;
    }
    
    void inOrderTraversal(List<TreeNode> res, TreeNode root) {
        if (root == null) {
            return;
        }
        
        inOrderTraversal(res, root.left);
        res.add(root);
        inOrderTraversal(res, root.right);
    }
}
