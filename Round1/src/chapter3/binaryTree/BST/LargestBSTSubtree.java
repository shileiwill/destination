package chapter3.binaryTree.BST;
/**
 * 333. Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
 A subtree must include all of its descendants.
 Here's an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7

The Largest BST Subtree in this case is the highlighted one. 
 The return value is the subtree's size, which is 3. 

Hint:
 1. You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity. 

Follow up:
 Can you figure out ways to solve it with O(n) time complexity? 

 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;

public class LargestBSTSubtree {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		LargestBSTSubtree l = new LargestBSTSubtree();
		int r = l.largestBSTSubtree(n2);
		System.out.println(r);
	}

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Level order traversal
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 1;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isValidBST(node)) {
                    count = Math.max(count, countSize(node));
                }
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (count != 1) {
                return count;
            }
        }
        return 1;
    }
    
    int countSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countSize(root.left);
        int right = countSize(root.right);
        
        return left + right + 1;
    }
    
    public boolean isValidBST(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        TreeNode cur = root;
        long prev = Long.MIN_VALUE;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            TreeNode node = stack.pop();
            if (node.val <= prev) {
                return false;
            }
            prev = node.val;
            cur = node.right;
        }
        
        return true;
    }
    
    // O(n) solution. The magic is to use ResultType to hold all information
    class ResultType {
        int size;
        int smallest;
        int largest;
        
        ResultType(int size, int smallest, int largest) {
            this.size = size;
            this.smallest = smallest;
            this.largest = largest;
        }
    }
    int max = 0;
    public int largestBSTSubtree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    
    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        // .size == -1 means invalid under itself
        if (left.size == -1 || right.size == -1 || left.largest >= root.val || right.smallest <= root.val) {
            return new ResultType(-1, 0, 0); // smallest and largest can be anything
        }
        
        int size = left.size + 1 + right.size;
        max = Math.max(size, max);
        
//        return new ResultType(size, left.smallest, right.largest); Not necessary to be a valid BST, so need to find the max, min
        return new ResultType(size, Math.min(left.smallest, root.val), Math.max(right.largest, root.val));
    }
}
