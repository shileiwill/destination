package company.tripadvisor.trialpay;

import chapter3.binaryTree.TreeNode;

public class SortedArrayToBST {

	public static void main(String[] args) {
		SortedArrayToBST sa = new SortedArrayToBST();
		int[] arr = {1, 2, 3, 4, 5, 6};
		TreeNode root = sa.arrayToBST(arr, 0, arr.length - 1);
		
		System.out.println(root.val);
	}

	TreeNode arrayToBST(int[] arr, int start, int end) {
		if (start > end) {
			return null;
		}
		
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		root.left = arrayToBST(arr, start, mid - 1);
		root.right = arrayToBST(arr, mid + 1, end);
		
		return root;
	}
}
