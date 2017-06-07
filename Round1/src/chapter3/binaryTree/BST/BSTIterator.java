package chapter3.binaryTree.BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    public TreeNode next() {
        return queue.poll();
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
    	
    	TreeNode node = stack.pop();
    	cur = node.right; // Move to right for next iteration, as the next smallest will be there
    	
        return node.val;
    }
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

// Another version, which is not efficient, but easy to think/implement
class BSTIterator3 {
    List<TreeNode> res = new ArrayList<TreeNode>();
    int pointer = 0;
    //@param root: The root of binary tree.
    public BSTIterator3(TreeNode root) {
        // 这实际上就是inorder traversal的iteration
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            TreeNode node = stack.pop();
            res.add(node);
            cur = node.right;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        // write your code here
        return pointer < res.size();
    }
    
    //@return: return next node
    public TreeNode next() {
        // write your code here
        TreeNode next = res.get(pointer++);
        return next;
    }
}
