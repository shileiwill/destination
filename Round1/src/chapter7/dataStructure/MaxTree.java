package chapter7.dataStructure;

import chapter3.binaryTree.TreeNode;

/**
 * Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.
Example
Given [2, 5, 6, 0, 3, 1], the max tree is

              6

            /    \

         5       3

       /        /   \

     2        0     1

 

 

Challenge
O(n) time complexity
 * @author Lei
 *
 */
public class MaxTree {

	public static void main(String[] args) {
		int[] A = {2, 5, 6, 0, 3, 1};
		MaxTree mt = new MaxTree();
		TreeNode root = mt.maxTree(A);
		
	}

	// Divide and Conquer
	 public TreeNode maxTree(int[] A) {
		 TreeNode root = helper(A, 0, A.length - 1);
		 return root;
	 }
	 
	 TreeNode helper(int[] A, int left, int right) {
		 if (left > right) {
			 return null;
		 }
		 
		 int maxIndex = left;
		 for (int i = left; i <= right; i++) {
			 if (A[i] > A[maxIndex]) {
				 maxIndex = i;
			 }
		 }
		 
		 TreeNode root = new TreeNode(A[maxIndex]);
		 root.left = helper(A, left, maxIndex - 1);
		 root.right = helper(A, maxIndex + 1, right);
		 
		 return root;
	 }
	 /** 可以用一个Stack做到O(n)
	  * 这个题Leetcode上没有，其实这种树叫做笛卡树（ Cartesian tree）。直接递归建树的话复杂度最差会退化到O(n^2)。经典建树方法，用到的是单调堆栈。我们堆栈里存放的树，只有左子树，没有有子树，且根节点最大。
（1） 如果新来一个数，比堆栈顶的树根的数小，则把这个数作为一个单独的节点压入堆栈。
（2） 否则，不断从堆栈里弹出树，新弹出的树以旧弹出的树为右子树，连接起来，直到目前堆栈顶的树根的数大于新来的数。然后，弹出的那些数，已经形成了一个新的树，这个树作为新节点的左子树，把这个新树压入堆栈。

这样的堆栈是单调的，越靠近堆栈顶的数越小。
最后还要按照（2）的方法，把所有树弹出来，每个旧树作为新树的右子树。
	  */
//	 17     public TreeNode maxTree(int[] A) {
//		 18         if (A.length==0) return null;
//		 19 
//		 20         Stack<TreeNode> nodeStack = new Stack<TreeNode>();
//		 21         nodeStack.push(new TreeNode(A[0]));
//		 22         for (int i=1;i<A.length;i++)
//		 23             if (A[i]<=nodeStack.peek().val){
//		 24                 TreeNode node = new TreeNode(A[i]);
//		 25                 nodeStack.push(node);
//		 26             } else {
//		 27                 TreeNode n1 = nodeStack.pop();
//		 28                 while (!nodeStack.isEmpty() && nodeStack.peek().val < A[i]){
//		 29                     TreeNode n2 = nodeStack.pop();
//		 30                     n2.right = n1;
//		 31                     n1 = n2;
//		 32                 }
//		 33                 TreeNode node = new TreeNode(A[i]);
//		 34                 node.left = n1;
//		 35                 nodeStack.push(node);
//		 36             }
//		 37         
//		 38 
//		 39         TreeNode root = nodeStack.pop();
//		 40         while (!nodeStack.isEmpty()){
//		 41             nodeStack.peek().right = root;
//		 42             root = nodeStack.pop();
//		 43         }
//		 44 
//		 45         return root;
//		 46 
//		 47     
//		 48             
//		 49             
//		 50 
//		 51     }
}
