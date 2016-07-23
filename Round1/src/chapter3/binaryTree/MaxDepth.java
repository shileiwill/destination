package chapter3.binaryTree;
/**
 * 104. Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * @author Lei
 *
 */
public class MaxDepth {
    public int maxDepthRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Divide
        int left = maxDepthRecursion(root.left);
        int right = maxDepthRecursion(root.right);
        // Conquer
        return Math.max(left, right) + 1;
    }
}
