package chapter3.binaryTree;
/**
 * 111. Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * @author Lei
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
}
