package chapter3.binaryTree.BST;
/**
 * 285. Given a binary search tree and a node in it, find the in-order successor of that node in the BST. 

Note: If the given node has no in-order successor in the tree, return null. 

 */
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class InOrderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        TreeNode prev = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            TreeNode node = stack.pop();
            if (prev == p) {
                return node;
            }
            prev = node;
            cur = node.right;
        }
        return null;
    }
}
