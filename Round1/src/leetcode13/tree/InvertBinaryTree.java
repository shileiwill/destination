package leetcode13.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 226. Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */
public class InvertBinaryTree {
    public TreeNode invertTreeRecursion(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.right = invertTree(left);
        root.left = invertTree(right);
        
        return root;
    }
    
    // DFS using Stack
    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        
        return root;
    }
    
    // BFS using Queue
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        
        return root;
    }
}
