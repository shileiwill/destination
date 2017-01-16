package leetcode13.tree;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

/**
 * 257. Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
 */
public class BinaryTreePaths {
    // Dont first think of Iteration, use Recursion, especially for DFS
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if (root == null) {
            return res;    
        }
        helper(root, "", res);
        return res;
    }
    
    void helper(TreeNode node, String path, List<String> res) {
        if (node.left == null && node.right == null) { // When to end
            res.add(path + node.val);
            return;
        }
        if (node.left != null) {
            helper(node.left, path + node.val + "->", res);
        }
        if (node.right != null) {
            helper(node.right, path + node.val + "->", res);
        }
    }
}
