package company.uber;

import chapter3.binaryTree.TreeNode;

/**
 * 类似于leetcode 101 symmetric tree。但是是要求将一个tree变成它的镜像树，然后返回镜像树的根节点。
 */
public class SymmetricTree {

	public static void main(String[] args) {
		SymmetricTree st = new SymmetricTree();
		
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		n4.left = n6;
		n6.right = n8;
		n8.left = n7;
		n1.right = n9;
		
		TreeNode res = st.helper(n2);
		System.out.println(res.val);
	}

	TreeNode helper(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		TreeNode newRoot = new TreeNode(root.val);
		newRoot.left = helper(root.right);
		newRoot.right = helper(root.left);
		
		return newRoot;
	}
}
