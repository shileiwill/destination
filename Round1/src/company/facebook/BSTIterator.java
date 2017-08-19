package company.facebook;

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

A follow up, Iterator for a list of BSTs (heap contain each BST’s iterator)
 *
 */
public class BSTIterator {

	/*
	 * given BST and two vals(lo an hi), return sum of nodes whose val in [lo , hi]。 O(N) and O(logN) solution
	 * O(N): Just iterate through the tree and find out any nodes in [lo , hi]
	 * O(logN): If the getSum(int low, int high) is called multiple times, Could preprocess the tree, and add sum field in each node,
	 * Next time, just use getSum(high) - getSum(first element smaller than low), which will be 2log(N)
	 */
	TreeNode rebuildTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		int runningSum = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		
		while (cur != null || !stack.isEmpty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			
			TreeNode node = stack.pop();
			runningSum += node.val;
			node.sum = runningSum;
			
			cur = node.right;
		}
		
		return root;
	}
	
	int getRangeSumInBST(TreeNode root, int low, int high) {
		int lowSum = helper(root, low);
		int highSum = helper(root, high);
		
		return highSum - lowSum; // Sum includes current node's value
	}
	
	int helper(TreeNode root, int target) { // Find the value which is first smaller than or equal to target
		TreeNode prev = null;
		
		while (root != null) {
			if (root.val == target) {
				return root.sum;
				//break;
			}
			
			prev = root;
			if (root.val < target) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		
		if (prev.val > target) {
			return 0; // In case there is no node smaller than target
		}
		
		return prev.sum;
	}
	
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

// Better
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
