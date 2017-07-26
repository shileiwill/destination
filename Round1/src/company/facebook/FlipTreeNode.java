package company.facebook;

/**
 * 一个完全树。TreeNode有parent指针。
每个TreeNode的值为 0或 1
每个parent的值为两个子TreeNode的 “and” 结果
现在把一个leaf翻牌子（0变1或者1变0）.
把树修正一遍

好题，虽然有parent node但是还是得递归
 */
public class FlipTreeNode {
	static class TreeNode {
		TreeNode left;
		TreeNode right;
		TreeNode parent;
		int val;
		
		TreeNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(0);
		TreeNode n2 = new TreeNode(1);
		TreeNode n3 = new TreeNode(0);
//		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(1);
		TreeNode n6 = new TreeNode(0);
		TreeNode n7 = new TreeNode(1);
		
		n1.left = n2;
		n1.right = n3;
//		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		
//		n4.parent = n2;
		n5.parent = n2;
		n6.parent = n3;
		n7.parent = n3;
		n2.parent = n1;
		n3.parent = n1;
		
		flip(n5);
		
		System.out.println(n1);
	}

	static void flip(TreeNode node) {
		if (node == null) {
			return;
		}
		
		
		if (node.parent == null) {
			flipCurrentNode(node);
			return;
		}
		
		int curVal = node.val;
		TreeNode parent = node.parent;
		// 分情况讨论 这里cover了不是完全树的情况
		if (curVal == 0) {
			if (parent.left == null || parent.right == null) {
				flipCurrentNode(node);
				flip(parent);
			} else if (parent.left.val == 1 || parent.right.val == 1) { // I dont know current node is left or right, but I know current value is 0
				flipCurrentNode(node);
				flip(parent);
			} // If both current node and the sibling node are 0, even if one node is flipped, parent doesnt need to change
		} else { // Current Node is 1
			if (parent.val == 0) { // Sibling node is 0
				flipCurrentNode(node);
			} else { // Sibling node is 1.
				flipCurrentNode(node);
				flip(parent);
			}
		}
	}

	private static void flipCurrentNode(TreeNode node) {
		// node.val = 1 - node.val;
		if (node.val == 1) {
			node.val = 0;
		} else {
			node.val = 1;
		}
	}
}
