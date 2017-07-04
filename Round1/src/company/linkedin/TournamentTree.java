package company.linkedin;

import chapter3.binaryTree.TreeNode;

/**
 * Tournament tree 找secMin; It is complete binary tree

Tournament tree 的定义是parent 是孩子node的最小值， 如下例 return 5

               2
             /   \
            2     7
          /  \   / \
         5    2 8   7 
        然后我问小哥要提示，小哥说第二名只能被最后的冠军打败。
所以我就想到只需要考虑被root打败过的node就可以了，就想到了O(logn)的解法，写出来了
 */
public class TournamentTree {

	public static void main(String[] args) {
		TreeNode n21 = new TreeNode(2);
		TreeNode n22 = new TreeNode(2);
		TreeNode n71 = new TreeNode(7);
		TreeNode n51 = new TreeNode(5);
		TreeNode n23 = new TreeNode(2);
		TreeNode n8 = new TreeNode(8);
		TreeNode n72 = new TreeNode(7);
		
		n21.left = n22;
		n21.right = n71;
		n22.left = n51;
		n22.right = n23;
		n71.left = n8;
		n71.right = n72;
		
		TournamentTree tt = new TournamentTree();
		int res = tt.findSecondMinRecursion(n21);
		
		System.out.println(res);
	}

	int findSecondMin(TreeNode root) {
		int secondMin = Integer.MAX_VALUE;
		
		while (root.left != null && root.right != null) {
			if (root.left.val == root.val) { // Left is smaller than right
				secondMin = Math.min(secondMin, root.right.val); // Potentially, root.right is the answer
				root = root.left; // Go to left to explore more
			} else {
				secondMin = Math.min(secondMin, root.left.val);
				root = root.right;
			}
		}
		
		return secondMin;
	}
	
	int findSecondMinRecursion(TreeNode root) {
		int secondMin = Integer.MAX_VALUE;
		
		if (root.left == null && root.right == null) {
			return Integer.MAX_VALUE;
		}
		
		if (root.val == root.left.val) {
			secondMin = Math.min(root.right.val, findSecondMinRecursion(root.left));
		} else {
			secondMin = Math.min(root.left.val, findSecondMinRecursion(root.right));
		}
		
		return secondMin;
	}
	
	// Follow up, how to find the Kth minimum? Once find the second min, change all first min to second min, and do a findSecondMin to find 3rd, ...
}
