package chapter3.binaryTree;
/**
 * 124. Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 * @author Lei
 *
 */
public class MaximumPathSum {

	public static void main(String[] args) {

	}
    // singleSum: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
    // maxSum: 从树中任意到任意点的最大路径，这条路径至少包含一个点
    class ResultType {
        int singleSum;
        int maxSum;
        
        ResultType(int singleSum, int maxSum) {
            this.singleSum = singleSum;
            this.maxSum = maxSum;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType rt = helper(root);
        
        return rt.maxSum;
    }
    
    ResultType helper(TreeNode node) {
        if (node == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        
        // Divide 
        ResultType left = helper(node.left);
        ResultType right = helper(node.right);
        
        // Conquer
        int singleSum = Math.max(left.singleSum, right.singleSum) + node.val;
        singleSum = Math.max(0, singleSum);
        
        int maxSum = Math.max(left.maxSum, right.maxSum);
        maxSum = Math.max(maxSum, left.singleSum + right.singleSum + node.val);
        
        return new ResultType(singleSum, maxSum);
    }
}
