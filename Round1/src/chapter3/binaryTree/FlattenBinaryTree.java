package chapter3.binaryTree;
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
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 */
import java.util.Stack;

public class FlattenBinaryTree {
    TreeNode prev = null;
    public void flatten(TreeNode root) { // PostOrder traversal
        if (root == null) {
            return;
        }
        
        flatten(root.right);
        flatten(root.left);
        
        root.right = prev;
        root.left = null;
        prev = root;
    }
    
    public void flattenStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode head = new TreeNode(root.val);
        TreeNode cur = head;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            cur.right = node;
            cur.left = null;
            cur = node;
            
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        
        root = head;
    }
}
