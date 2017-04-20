package company.amazon;

import chapter3.binaryTree.TreeNode;
import chapter3.binaryTree.BST.BSTIterator;
import leetcode13.tree.InorderSuccessorInBST;
/**
 * BST 组新树
给我一个BST,要求得到一颗新树,每个节点的内容是比此节点的元素值大的元素的总和。
我一看不会,赶紧想,就说用stack去做,然后他说为什么不对每个点分别 求,还给我一个函数原型,我说不要,这样重复计算,
我用stack可以n做出来,他说那你 写,我写啊写,写好了,他说不明白,给解释了很久,最后他终于明白了,简直要哭。
这里 我明白一个道理,你要是解法和面试官不一样,他们很多时候就懵了,也不好意思说看不 懂,然后很长时间就在怀疑你。
不过,面试后遇到cmu女神,她说这题见过,递归就行了, 我听着就泪奔了,还是要多训练,差距太大

538. Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
 */
public class BiggerTree {
	void newTreeOf2(TreeNode root) {
		if (root == null) {
			return;
		}
		BSTIterator it = new BSTIterator(root);
		helper(root, it);
	}
	
	void helper(TreeNode node, BSTIterator it) {
		if (it.hasNext()) {
			TreeNode next = it.next(); // This returns Node.
			int originalValInNext = next.val;
			helper(next, it); // DFS, go!
			node.val = next.val + originalValInNext; // next.val is changed already
		} else {
			node.val = 0; // No node is bigger than this one.
		}
	}
	
	// What will happen without iterator?
	InorderSuccessorInBST inorderSuccessor = new InorderSuccessorInBST();
	void newTreeOf3(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode smallest = findSmallest(root);
		helper(smallest, root);
	}
	
	TreeNode findSmallest(TreeNode node) {
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	void helper(TreeNode node, TreeNode root) {
		TreeNode next = inorderSuccessor.inorderSuccessor(root, node);
		if (next != null) {
			int originalValInNext = next.val;
			helper(next, root);
			node.val = next.val + originalValInNext;
		} else {
			node.val = 0;
		}
	}
	
	// What if there is parent node in each TreeNode
	void newTreeOf(TreeNodeWithParent root) {
		if (root == null) {
			return;
		}
		TreeNodeWithParent smallest = (TreeNodeWithParent) findSmallest(root);
		helper2(smallest);
	}
	
	void helper2(TreeNodeWithParent node) {
		TreeNodeWithParent next = nextNode(node);
		if (next != null) {
			int originalValInNext = next.val;
			helper2(next);
			node.val = next.val + originalValInNext;
		} else {
			node.val = 0;
		}
	}
	
	TreeNodeWithParent nextNode(TreeNodeWithParent node) {
		if (node.right != null) {
			TreeNodeWithParent N = node.right;
			while (N.left != null) {
				N = N.left;
			}
			
			return N;
		}
		
		// This is wrong, think over it
//		if (node.parent != null && node.parent.right != node) { // Node is not right child
//			TreeNodeWithParent N = node.parent.right;
//			while (N.left != null) {
//				N = N.left;
//			}
//			
//			return N;
//		}
		
        while (node.parent != null) {
            if (node == node.parent.left) { // 如果当前node是parent的左节点， parent 就是next
                return node.parent;
            }
            node = node.parent; // 如果当前节点是parent的右节点，得往上递推到上一个parent
        }
		
		return null;
	}
	
	class TreeNodeWithParent extends TreeNode{
		TreeNodeWithParent left;
		TreeNodeWithParent right;
		TreeNodeWithParent parent;
		int val;
		public TreeNodeWithParent(int x) {
			this.val = x;
		}
		public TreeNodeWithParent() {}
	}
}

