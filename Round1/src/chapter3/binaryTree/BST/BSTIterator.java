package chapter3.binaryTree.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;
/**
 * 173. Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * @author Lei
 *
 */
public class BSTIterator {

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    
    void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        queue.offer(node);
        helper(node.right);
    }
    // Build a full queue in constructor. It is heavy here
    public BSTIterator(TreeNode root) {
        helper(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll().val;
    }
}

class BSTIterator2 {

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur;
    
    public BSTIterator2(TreeNode root) {
        cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
    	while(cur != null) {
    		stack.push(cur);
    		cur = cur.left;
    	}
    	
    	cur = stack.pop();
    	TreeNode node = cur; // Take a note before we make changes to cur
    	cur = cur.right; // Move to right for next iteration, as the next smallest will be there
    	
        return node.val;
    }
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
