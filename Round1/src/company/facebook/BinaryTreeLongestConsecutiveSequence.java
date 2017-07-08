package company.facebook;

import chapter3.binaryTree.TreeNode;

/**
 * 298. Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
public class BinaryTreeLongestConsecutiveSequence {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helperMyStyle(root, 0, root.val);
        return max;
    }
    
    // A very naive DFS solution
    void helper(TreeNode node, int cur, int target) {
        if (node == null) {
            return;
        }
        // Looks like pre-order
        if (node.val == target) {
            cur++;
        } else {
            cur = 1;
        }
        
        max = Math.max(max, cur);
        helper(node.left, cur, node.val + 1);
        helper(node.right, cur, node.val + 1);
    }
    
    void helperMyStyle(TreeNode node, int cur, int target) {
        if (node == null) {
        	max = Math.max(max, cur);
            return;
        }
        
        if (node.val == target) {
        	helperMyStyle(node.left, cur + 1, target + 1);
        	helperMyStyle(node.right, cur + 1, target + 1);
        } else {
        	max = Math.max(max, cur);
        	
        	if (node.left != null) {
        		helperMyStyle(node.left, node.left.val, 0);
        	}
        	if (node.right != null) {
        		helperMyStyle(node.right, node.right.val, 0);
        	}
        }
        
    }
    
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(1);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(3);
    	TreeNode node22 = new TreeNode(2);
    	
    	node2.right = node3;
    	node3.left = node22;
    	node22.left = node1;
    	
    	BinaryTreeLongestConsecutiveSequence l = new BinaryTreeLongestConsecutiveSequence();
    	int res = l.longestConsecutive(node2);
    	
    	System.out.println(res);
	}
}
