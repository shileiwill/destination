package company.facebook;

import java.util.LinkedList;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

/**
 * 111. Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthOfBinaryTree {

    int res = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        helper(root, 0);
        return res;
    }
    
    void helper(TreeNode root, int height) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            res = Math.min(res, height + 1);
            return;        
        }
        
        helper(root.left, height + 1);
        helper(root.right, height + 1);
        
    }
    
    public int minDepth2(TreeNode root) {
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
    
    // This is good
    public int minDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                
                if (now.left == null && now.right == null) {
                    return level + 1;
                }
                
                if (now.left != null) {
                    queue.offer(now.left);
                }
                
                if (now.right != null) {
                    queue.offer(now.right);
                }
            }
            
            level++;
        }
        
        return 0;
    }

}
