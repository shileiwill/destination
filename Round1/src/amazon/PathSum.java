package amazon;

import chapter3.binaryTree.TreeNode;
/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Example :

Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Return 0 / 1 ( 0 for false, 1 for true ) for this problem



Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 NOTE : The path has to end on a leaf node. 
Example :

         1
        /
       2
min depth = 2.

 */
public class PathSum {

	public static void main(String[] args) {
		TreeNode n3 = new TreeNode(3);
		TreeNode n9 = new TreeNode(9);
		TreeNode n20 = new TreeNode(20);
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(7);
		
		n3.left = n9;
		n3.right = n20;
		n20.left = n15;
		n20.right = n7;
		
		PathSum lot = new PathSum();
		int res = lot.minDepth(n3);
		System.out.println(res);
	}

	public int minDepth(TreeNode a) {
	    if (a == null) {
	        return 0;
	    }
	    
	    return helper(a);
	}
	
	int helper(TreeNode node) {
	    if (node.left == null && node.right == null) {
	        return 1;     
	    }
	    
	    int left = Integer.MAX_VALUE;
	    if (node.left != null) {
    	    left = helper(node.left);
	    }
	    
	    int right = Integer.MAX_VALUE;
	    if (node.right != null) {
    	    right = helper(node.right);
	    }
	    
	    int res = Math.min(left, right) + 1;
	    
	    return res;
	}
	
	public int hasPathSum(TreeNode a, int b) {
	    if (a == null) {
	        return 0;
	    }
	    int[] res = {b};
        return helper2(a, res) ? 1 : 0;	    
	}
	
	boolean helper2(TreeNode node, int[] res) {
	    if (node.left == null && node.right == null) { // Leaf
	        if (res[0] == node.val) {
	            return true;
	        }
	        return false;
	    }
	    
	    
	    boolean left = false;
	    if (node.left != null) {
    	    res[0] -= node.val; 
    	    left = helper2(node.left, res);
    	    res[0] += node.val; 
	    }
	    
	    boolean right = false;
	    if (node.right != null) {
    	    res[0] -= node.val; 
    	    right = helper2(node.right, res);
    	    res[0] += node.val; 
	    }
	    
	    return left || right;
	}
}
