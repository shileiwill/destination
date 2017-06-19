package company.facebook;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

/**
 * print max depth path of a binary tree
 */
public class PrintMaxDepthPathOfBinaryTree {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		n5.right = n6;
		n6.left = n7;
		
		PrintMaxDepthPathOfBinaryTree pm = new PrintMaxDepthPathOfBinaryTree();
		pm.printMaxDepth(n2);
	}

	void printMaxDepth(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		helper(list, root, 1);
		
		System.out.println(max);
		for (int val : res) {
			System.out.print(val + "--");
		}
	}

	int max = 0;
	List<Integer> res = null;
	private void helper(List<Integer> list, TreeNode root, int level) { // 用return value as height不大好使
		if (root == null) {
			return;
		}
		
		if (root.left == null && root.right == null) {
			list.add(root.val);
			if (level > max) {
				res = new ArrayList<Integer>(list);
				max = Math.max(max, level);
			}
			list.remove(list.size() - 1);
			return;
		}
		
		list.add(root.val);
		helper(list, root.left, level + 1);
		helper(list, root.right, level + 1);
		list.remove(list.size() - 1);
	}
	
	
}
