package company.facebook;

import java.util.HashMap;
import java.util.Map;

import chapter3.binaryTree.TreeNode;

public class LargestDistance {

	public static void main(String[] args) {
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
		
		LargestDistance ld = new LargestDistance();
		int res = ld.height(n2);
		System.out.println(ld.max);
	}

	Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
	int max = 0;
	
	int height(TreeNode node) {
		if (node == null) {
			return 0;
		}

		if (map.containsKey(node)) {
			System.out.println("NEver come here");
			return map.get(node);
		}
		
		int left = height(node.left);
		int right = height(node.right);
		int res = Math.max(left, right) + 1;
		
		max = Math.max(max, left + right + 1);
		System.out.println(node.val + "--" + left + "--" + right + "--" + res);
		
		map.put(node, res);
		return res;
	}
}
