package company.amazon;

import java.util.ArrayList;

import chapter3.binaryTree.TreeNode;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path’s sum equals the given sum.

For example:
Given the below binary tree and sum = 22,

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return

[
   [5,4,11,2],
   [5,8,4,5]
]
 */
public class RootToLeafPathsWithSum {

	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    
	    if (root == null) {
	        return res;
	    }
	    helper(res, list, root, sum);
	    return res;
	}
	
	void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, TreeNode node, int sum) {
	    if (node.left == null && node.right == null) {
	        if (sum == node.val) {
	            ArrayList<Integer> copy = new ArrayList<Integer>(list);
	            copy.add(node.val);
	            res.add(copy);
	        }
	        return;
	    }
	    
	    list.add(node.val);
	    if (node.left != null) {
	        helper(res, list, node.left, sum - node.val);
	    }
	    
	    if (node.right != null) {
	        helper(res, list, node.right, sum - node.val);
	    }
	    list.remove(list.size() - 1);
	    
	}

}
