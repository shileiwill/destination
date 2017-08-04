package company.linkedin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * New and Hot
 * 
 * 270. Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * 
 * 272. Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 */
public class ClosestBinarySearchTreeValue {
	// Need to find only 1 closest. Go left or right, O(logN)
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;
        int closest = Integer.MAX_VALUE;
        
        while (cur != null) {
            if (cur.val == target) {
                return cur.val;
            }
            
            if (closest == Integer.MAX_VALUE || Math.abs(cur.val - target) < Math.abs(closest - target)) {
                closest = cur.val;
            }
            
            if (cur.val > target) {
                cur = cur.left;    
            } else {
                cur = cur.right;
            }
        }
        
        return closest;
    }
    
    // Disregard the fact that it is a BST, just preorder traversal all. O(N)
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        PriorityQueue<TreeNode> heap = new PriorityQueue<TreeNode>(k, new Comparator<TreeNode>(){
            public int compare(TreeNode node1, TreeNode node2) {
                double diff1 = Math.abs(node1.val - target);
                double diff2 = Math.abs(node2.val - target);
                
                // Max heap
                return (int)(diff2 - diff1);
            }
        });
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode now = stack.pop();
            
            if (heap.size() < k) {
                heap.offer(now);
            } else {
                double diff = Math.abs(now.val - target);
                double peek = Math.abs(heap.peek().val - target);
                
                if (peek > diff) {
                    heap.poll();
                    heap.offer(now);
                }
            }
            
            if (now.right != null) {
                stack.push(now.right);
            }
            
            if (now.left != null) {
                stack.push(now.left);
            }
        }
        
        List<Integer> res = new ArrayList<Integer>();
        while (!heap.isEmpty()) {
            res.add(heap.poll().val);
        }
        
        return res;
    }
    
    // inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.
    // O(n + k)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> bigger = new Stack<TreeNode>();
        Stack<TreeNode> smaller = new Stack<TreeNode>();
        
        inorderBigger(root, target, k, bigger);
        inorderSmaller(root, target, k, smaller);
        
        List<Integer> res = new ArrayList<Integer>();
        while (res.size() < k) {
            if (smaller.isEmpty()) {
                res.add(bigger.pop().val);
            } else if (bigger.isEmpty()) {
                res.add(smaller.pop().val);
            } else if (Math.abs(smaller.peek().val - target) < Math.abs(bigger.peek().val - target)) {
                res.add(smaller.pop().val);
            } else {
                res.add(bigger.pop().val);
            }
        }
        
        return res;
    }
    
    void inorderBigger(TreeNode node, double target, int k, Stack<TreeNode> stack) {
        if (node == null) {
            return;
        }
        
        inorderBigger(node.right, target, k, stack);
        
        if (node.val < target) { // Terminate earlier
            return;
        }
        stack.push(node);
        
        inorderBigger(node.left, target, k, stack);
    }
    
    void inorderSmaller(TreeNode node, double target, int k, Stack<TreeNode> stack) {
        if (node == null) {
            return;
        }
        
        inorderSmaller(node.left, target, k, stack);
        
        if (node.val >= target) {
            return;
        }
        stack.push(node);
        
        inorderSmaller(node.right, target, k, stack);
    }
    
    // Above 2 inorder traversals can be merged together
    void inorder(TreeNode node, double target, int k, boolean reverse, Stack<Integer> stack) {
        if (node == null) {
            return;
        }
        
        inorder(reverse ? node.right : node.left, target, k, reverse, stack);
        
        // Stop, no need to go through the whole tree
        if ((reverse && node.val <= target) || (!reverse && node.val > target)) {
            return;
        }
        stack.push(node.val);
        
        inorder(reverse ? node.left : node.right, target, k, reverse, stack);
    }
    
    public static void main(String[] args) {
    	ClosestBinarySearchTreeValue cb = new ClosestBinarySearchTreeValue();
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node1 = new TreeNode(1);
    	node2.left = node1;
    	
    	double target = 4.142857;
    	int k = 2;
    	
    	List<Integer> res = cb.closestKValues(node2, target, k);
    	for (int val : res) {
    		System.out.println(val);
    	}
	}
}
