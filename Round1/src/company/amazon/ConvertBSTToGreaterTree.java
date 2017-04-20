package company.amazon;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;
/**
 * 538. Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */
public class ConvertBSTToGreaterTree {

	public static void main(String[] args) {
		TreeNode n5 = new TreeNode(5);
		TreeNode n2 = new TreeNode(2);
		TreeNode n13 = new TreeNode(13);
		
		n5.left = n2;
		n5.right = n13;
		
		ConvertBSTToGreaterTree c = new ConvertBSTToGreaterTree();
		TreeNode n = c.convertBST(n5);
		System.out.println(n.val);
	}

	public TreeNode convertBST(TreeNode root) {
        BSTIterator it = new BSTIterator(root);
        helper(it);
        return root;
    }
    
    int helper(BSTIterator it) {
        if (it.hasNext()) {
            TreeNode node = it.getNext();
//            int originalVal = node.val;
            int res = helper(it);
//            node.val = originalVal + res;
            node.val = node.val + res;
            return node.val;
        } else {
            // node.val is still node.val
            // node.val = 0;
        	return 0;
        }
    }
}

class BSTIterator {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = null;
    
    BSTIterator(TreeNode node) {
        cur = node;
    }
    
    TreeNode getNext() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        
        TreeNode node = stack.pop();
        cur = node.right;
        
        return node;
    }
    
    boolean hasNext() {
        if (cur == null && stack.isEmpty()) {
            return false;
        }
        return true;
    }
}