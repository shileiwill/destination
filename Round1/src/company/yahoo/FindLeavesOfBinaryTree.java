package company.yahoo;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

public class FindLeavesOfBinaryTree {

	public static void main(String[] args) {

	}

	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(res, root);
		return res;
	}
	
	int helper(List<List<Integer>> res, TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(res,  root.left);
		int right = helper(res, root.right);
		
		int height = Math.max(left, right) + 1;
		
		if (height > res.size()) {
			res.add(new ArrayList<Integer>());
		}
		
		res.get(height - 1).add(root.val);
		
		return height;
	}
}
