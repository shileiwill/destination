package company.facebook;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 114. Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
 */
public class BinaryTreeFlattenToLinkedList {

    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        flatten(root.right);
        flatten(root.left);
        
        root.right = prev;
        root.left = null;
        prev = root;
    }

    // Pre order traversal
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy;
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            prev.right = node;
            
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            
            node.left = null;
            node.right = null;
            prev = prev.right;
        }
        
        root = dummy.right;
    }

}
