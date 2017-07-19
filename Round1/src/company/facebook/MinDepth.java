package company.facebook;

import chapter3.binaryTree.TreeNode;

/**
 * 111. Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

minimum depth of binary tree follow up : what if this tree left subtree may be infinite. bfs秒之
 *
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Leaf node, return the height of itself
        if (root.left == null && root.right == null) {
            return 1;
        }
        
        // Divide
        int left = Integer.MAX_VALUE;
        if (root.left != null) {
            left = minDepth(root.left);
        }
        
        int right = Integer.MAX_VALUE;
        if (root.right != null) {
            right = minDepth(root.right);
        }
        
        // Conquer
        return Math.min(left, right) + 1;
    }
    
    // Another version
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);
        
        if (root.left != null && root.right != null) { // have children on both sides
            return Math.min(left, right) + 1;
        } else if (root.left != null) { // Have child only on left
            return left + 1;
        } else if (root.right != null) { // Have child only on right
            return right + 1;
        } else { // no children at all, itself
            return 1;
        }
    }
}
