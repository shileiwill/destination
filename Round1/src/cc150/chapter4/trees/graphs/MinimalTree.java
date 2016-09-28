package cc150.chapter4.trees.graphs;

import chapter3.binaryTree.TreeNode;

public class MinimalTree {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		TreeNode root = minTree(arr, 0, arr.length - 1);
		System.out.println(root.val);
	}

	static TreeNode minTree(int[] arr, int left, int right) {
//		if (left == right) { This is not required at all....
//			return new TreeNode(arr[right]);
//		}
		if (left > right) {
			return null;
		}
		int midIndex = (left + right) / 2;
		TreeNode root = new TreeNode(arr[midIndex]);
		root.left = minTree(arr, left, midIndex - 1);
		root.right = minTree(arr, midIndex + 1, right);
		
		return root;
	}
}
