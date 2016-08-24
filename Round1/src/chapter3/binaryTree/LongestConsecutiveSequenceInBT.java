package chapter3.binaryTree;
/**
 * 298. Given a binary tree, find the length of the longest consecutive sequence path. 



The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse). 

For example,

   1
    \
     3
    / \
   2   4
        \
         5

Longest consecutive sequence path is 3-4-5, so return 3.    2
    \
     3
    / 
   2    
  / 
 1

Longest consecutive sequence path is 2-3,not3-2-1, so return 2. 
 */
import java.util.LinkedList;
import java.util.Queue;

public class LongestConsecutiveSequenceInBT {
	// Iteration
	public int longestConsecutive(TreeNode root) {
	        if (root == null) {
	            return 0;
	        }
	        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
	        Queue<Integer> counts = new LinkedList<Integer>(); // Every TreeNode has a corresponding count
	        
	        int max2 = 1;
	        nodes.offer(root);
	        counts.offer(1);
	        
	        while (!nodes.isEmpty()) {
	            // Unlike level order traversal, We dont need to use for loop to have levels for this. Just go through all
	            TreeNode node = nodes.poll();
	            int count = counts.poll();
	            
	            int leftCount = 1;
	            if (node.left != null) {
	                if (node.left.val - 1 == node.val) {
	                    leftCount = count + 1;
	                    max2 = Math.max(max2, leftCount);
	                }
	                nodes.offer(node.left);
	                counts.offer(leftCount);
	            }
	            
	            int rightCount = 1;
	            if (node.right != null) {
	                if (node.right.val - 1 == node.val) {
	                    rightCount = count + 1;
	                    max2 = Math.max(max2, rightCount);
	                }
	                nodes.offer(node.right);
	                counts.offer(rightCount);
	            }
	        }
	        
	        return max2;
	    }
	// Divide and Conquer
	    int max = 1;
	    public int longestConsecutive2(TreeNode root) {
	        if (root == null) {
	            return 0;
	        }
	        helper(root);        
	        return max;
	    }
	    
	    int helper(TreeNode root) {
	        if (root == null) {
	            return 0;
	        }
	        
	        int left = helper(root.left);
	        int right = helper(root.right);
	        
	        int leftCount = 1;
	        if (root.left != null && root.left.val - 1 == root.val) {
	            leftCount = left + 1;
	            max = Math.max(max, leftCount);
	        } // Else are all 1, either root.left is null or not more than 1
	        
	        int rightCount = 1;
	        if (root.right != null && root.right.val - 1 == root.val) {
	            rightCount = right + 1;
	            max = Math.max(max, rightCount);
	        }    
	        
	        return Math.max(leftCount, rightCount);
	    }
}

// Unofortunately, mine is not working, passing 52 out of 54 test cases
//public class LongestConsecutiveSequenceInBT {
//
//	public static void main(String[] args) {
//		LongestConsecutiveSequenceInBT l = new LongestConsecutiveSequenceInBT();
//		int res = l.longestConsecutive(root);
//		System.out.println(res);
//	}
//	
//    public int longestConsecutive(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        ResultType res = helper(root);
//         return Math.max(res.withMax, res.totalMax);
////        return max;
//    }
//    ResultType helper(TreeNode root) {
//        if (root == null) {
//            return new ResultType(0, 0, Integer.MIN_VALUE);
//        }
//        
//        ResultType left = helper(root.left);
//        ResultType right = helper(root.right);
//        
//        int withMaxLeft = 1;
//        if (left.curVal != Integer.MIN_VALUE && left.curVal - 1 == root.val) {
//            withMaxLeft = left.withMax + 1;
//        }
//        int withMaxRight = 1;
//        if (right.curVal != Integer.MIN_VALUE && right.curVal - 1 == root.val) {
//            withMaxRight = right.withMax + 1;
//        }
//        
//        int totalMaxLeft = Math.max(left.totalMax, withMaxLeft);
//        int totalMaxRight = Math.max(right.totalMax, withMaxRight);
//        
//        ResultType r = new ResultType(Math.max(totalMaxLeft, totalMaxRight), Math.max(withMaxLeft, withMaxRight), root.val);
//        max = Math.max(max, r.totalMax);
//        return r;
//    }
//}
//
//class ResultType {
//    int withMax;
//    int totalMax;
//    int curVal;
//    
//    ResultType (int withMax, int totalMax, int curVal) {
//        this.withMax = withMax;
//        this.totalMax = totalMax;
//        this.curVal = curVal;
//    }
//}