package chapter1.pickOne;

import chapter3.binaryTree.TreeNode;

public class ConvertArrayToBST {

	public static void main(String[] args) {
		ConvertArrayToBST c = new ConvertArrayToBST();
		int[] arr = {1, 2, 3, 4};
		TreeNode root = c.bstOf(arr);
		System.out.println(root.val);
	}

	TreeNode bstOf(int[] arr) {
		return helper(arr, 0, arr.length - 1);
	}
	
	TreeNode helper(int[] arr, int left, int right) {
//		if (left == right) {
//			return new TreeNode(arr[left]);
//		}
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		
		root.left = helper(arr, left, mid - 1);
		root.right = helper(arr, mid + 1, right);
		
		return root;
	}
}
