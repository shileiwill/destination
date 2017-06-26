package company.facebook;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 285. Inorder Successor in BST
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
 */
public class InOrderSuccessorInBST {

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            TreeNode node = stack.pop();
            if (pre != null && pre == p) { // 直接 pre != p?
                return node;
            }
            
            pre = node;
            cur = node.right;
        }
        
        return null;
    }
    
    // Remember this guy!!! without using extra space
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        
        TreeNode n = root;
        TreeNode pre = null;
        while (n != null) {
            if (p.val > n.val) {
                n = n.right;
            } else if (p.val < n.val) {
                pre = n;
                n = n.left;
            } else {
                break;
            }
        }
        
        return pre;
    }

}
